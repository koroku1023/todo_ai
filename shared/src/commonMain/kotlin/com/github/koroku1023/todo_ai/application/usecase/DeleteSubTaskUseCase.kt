package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant
import com.github.koroku1023.todo_ai.domain.model.SubTask
import com.github.koroku1023.todo_ai.domain.repository.SubTaskRepository

class DeleteSubTaskUseCase(private val subTaskRepository: SubTaskRepository) {
  fun execute(subTaskId: String, now: Instant): SubTask {
    val subTask = subTaskRepository.findById(subTaskId)
      ?: throw IllegalArgumentException("SubTask not found: $subTaskId")
    return subTaskRepository.save(subTask.markAsDeleted(now))
  }
}
