package com.github.koroku1023.todo_ai.application.usecase

import com.github.koroku1023.todo_ai.domain.repository.ProjectRepository
import com.github.koroku1023.todo_ai.domain.model.Project

class FetchProjectsUseCase(
  private val projectRepository: ProjectRepository
) {
  fun execute(): List<Project> = projectRepository.findAll()
}
