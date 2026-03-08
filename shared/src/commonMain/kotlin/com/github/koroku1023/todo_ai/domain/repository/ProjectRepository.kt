package com.github.koroku1023.todo_ai.domain.repository

import com.github.koroku1023.todo_ai.domain.model.Project

interface ProjectRepository {
  fun findAll(): List<Project>
  fun findById(id: String): Project?
  fun save(project: Project): Project
  fun delete(project: Project)
}
