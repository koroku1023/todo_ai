package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant

import com.github.koroku1023.todo_ai.domain.model.Task
import com.github.koroku1023.todo_ai.domain.repository.TaskRepository

class DeleteTaskUseCase(
  private val taskRepository: TaskRepository
) {
  fun execute(taskId: String, now: Instant): Task {
    val task = taskRepository.findById(taskId)
      ?: throw IllegalArgumentException("Task not found: $taskId")
    return taskRepository.save(task.markAsDeleted(now))
  }
}
