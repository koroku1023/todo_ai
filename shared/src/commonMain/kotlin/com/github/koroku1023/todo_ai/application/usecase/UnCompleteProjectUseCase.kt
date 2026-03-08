package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant

import com.github.koroku1023.todo_ai.domain.model.Project
import com.github.koroku1023.todo_ai.domain.repository.ProjectRepository

class UnCompleteProjectUseCase(
  private val projectRepository: ProjectRepository
) {
  fun execute(projectId: String, now: Instant): Project {
    val project = projectRepository.findById(projectId)
      ?: throw IllegalArgumentException("Project not found: $projectId")
    return projectRepository.save(project.unComplete(now))
  }
}
