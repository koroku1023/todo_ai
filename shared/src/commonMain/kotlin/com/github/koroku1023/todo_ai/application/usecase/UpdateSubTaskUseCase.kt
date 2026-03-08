package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant

import com.github.koroku1023.todo_ai.domain.model.SubTask
import com.github.koroku1023.todo_ai.domain.repository.SubTaskRepository

data class SubTaskUpdate(
  val title: String,
  val memo: String?,
  val startAt: Instant?,
  val endAt: Instant?,
  val repeatDays: List<String>?,
)

class UpdateSubTaskUseCase(
  private val subTaskRepository: SubTaskRepository
) {
  fun execute(subTaskId: String, update: SubTaskUpdate, now: Instant): SubTask {
    val subTask = subTaskRepository.findById(subTaskId)
      ?: throw IllegalArgumentException("SubTask not found: $subTaskId")
    val updated = subTask.updateDetails(update.title, update.memo, update.startAt, update.endAt, update.repeatDays, now)
    return subTaskRepository.save(updated)
  }
}
