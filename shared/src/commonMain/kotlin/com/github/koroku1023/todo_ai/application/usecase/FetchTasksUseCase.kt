package com.github.koroku1023.todo_ai.application.usecase

import com.github.koroku1023.todo_ai.domain.model.Task
import com.github.koroku1023.todo_ai.domain.repository.TaskRepository

class FetchTasksUseCase(
  private val taskRepository: TaskRepository
) {
  fun execute(projectId: String): List<Task> = taskRepository.findByProjectId(projectId)
}
