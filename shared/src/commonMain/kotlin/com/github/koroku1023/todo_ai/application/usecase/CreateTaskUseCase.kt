package com.github.koroku1023.todo_ai.application.usecase

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.datetime.Instant


import com.github.koroku1023.todo_ai.domain.repository.TaskRepository
import com.github.koroku1023.todo_ai.domain.model.Task

class CreateTaskUseCase(
  private val taskRepository: TaskRepository
) {
  @OptIn(ExperimentalUuidApi::class)
  fun execute(projectId: String, title: String, now: Instant, color: String? = null): Task {
    val task =
      Task.create(id = Uuid.random().toString(), projectId = projectId, title = title, now = now, color = color)
    return taskRepository.save(task)
  }
}
