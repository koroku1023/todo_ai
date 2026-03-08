package com.github.koroku1023.todo_ai.application.usecase

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.datetime.Instant

import com.github.koroku1023.todo_ai.domain.repository.SubTaskRepository
import com.github.koroku1023.todo_ai.domain.model.SubTask

data class SubTaskCreate(
  val taskId: String,
  val title: String,
  val memo: String?,
  val startAt: Instant?,
  val endAt: Instant?,
  val repeatDays: List<String>?,
)

class CreateSubTaskUseCase(
  private val subTaskRepository: SubTaskRepository
) {
  @OptIn(ExperimentalUuidApi::class)
  fun execute(params: SubTaskCreate, now: Instant): SubTask {
    val subTask = SubTask.create(
      id = Uuid.random().toString(),
      taskId = params.taskId,
      title = params.title,
      now = now,
      memo = params.memo,
      startAt = params.startAt,
      endAt = params.endAt,
      repeatDays = params.repeatDays
    )
    return subTaskRepository.save(subTask)
  }
}
