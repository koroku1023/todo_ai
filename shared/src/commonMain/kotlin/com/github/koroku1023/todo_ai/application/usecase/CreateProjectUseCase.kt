package com.github.koroku1023.todo_ai.application.usecase

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.datetime.Instant

import com.github.koroku1023.todo_ai.domain.repository.ProjectRepository
import com.github.koroku1023.todo_ai.domain.model.Project

class CreateProjectUseCase(
  private val projectRepository: ProjectRepository
) {
  @OptIn(ExperimentalUuidApi::class)
  fun execute(title: String, now: Instant): Project {
    val project = Project.create(id = Uuid.random().toString(), title = title, now = now)
    return projectRepository.save(project)
  }
}
