package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant

import com.github.koroku1023.todo_ai.domain.model.Project
import com.github.koroku1023.todo_ai.domain.repository.ProjectRepository

data class ProjectUpdate(
  val title: String,
  val color: String?,
  val isFavorite: Boolean,
)

class UpdateProjectUseCase(
  private val projectRepository: ProjectRepository
) {
  fun execute(projectId: String, update: ProjectUpdate, now: Instant): Project {
    val project = projectRepository.findById(projectId)
      ?: throw IllegalArgumentException("Project not found: $projectId")
    val updated = project.update(update.title, update.color, update.isFavorite, now)
    return projectRepository.save(updated)
  }
}
