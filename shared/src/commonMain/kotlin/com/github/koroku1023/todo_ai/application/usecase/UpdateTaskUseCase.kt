package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant

import com.github.koroku1023.todo_ai.domain.model.Task
import com.github.koroku1023.todo_ai.domain.repository.TaskRepository

data class TaskUpdate(
  val title: String,
  val memo: String?,
  val color: String?,
  val projectId: String,
)

class UpdateTaskUseCase(
  private val taskRepository: TaskRepository
) {
  fun execute(taskId: String, update: TaskUpdate, now: Instant): Task {
    val task = taskRepository.findById(taskId)
      ?: throw IllegalArgumentException("Task not found: $taskId")
    val updated = task.update(update.title, update.memo, update.color, update.projectId, now)
    return taskRepository.save(updated)
  }
}
