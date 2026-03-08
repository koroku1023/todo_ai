package com.github.koroku1023.todo_ai.application.usecase

import com.github.koroku1023.todo_ai.domain.model.SubTask
import com.github.koroku1023.todo_ai.domain.repository.SubTaskRepository

class FetchSubTasksUseCase(private val subTaskRepository: SubTaskRepository) {
  fun execute(taskId: String): List<SubTask> {
    return subTaskRepository.findByTaskId(taskId)
  }
}
