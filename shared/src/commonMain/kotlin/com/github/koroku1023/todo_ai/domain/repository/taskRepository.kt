package com.github.koroku1023.todo_ai.domain.repository

import com.github.koroku1023.todo_ai.domain.model.Task

interface TaskRepository {
  fun findAll(): List<Task>
  fun findByProjectId(projectId: String): List<Task>
  fun findById(id: String): Task?
  fun save(task: Task): Task
  fun delete(task: Task)
}
