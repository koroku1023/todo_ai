package com.github.koroku1023.todo_ai.application.usecase

import com.github.koroku1023.todo_ai.domain.model.Project
import com.github.koroku1023.todo_ai.domain.repository.ProjectRepository

class FakeProjectRepository : ProjectRepository {
  private val store = mutableListOf<Project>()

  override fun findAll(): List<Project> = store.toList()

  override fun findById(id: String): Project? = store.find { it.id == id }

  override fun save(project: Project): Project {
    store.removeAll { it.id == project.id }
    store.add(project)
    return project
  }

  override fun delete(project: Project) {
    store.removeAll { it.id == project.id }
  }
}
