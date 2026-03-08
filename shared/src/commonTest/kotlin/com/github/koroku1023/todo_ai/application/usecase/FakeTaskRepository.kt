package com.github.koroku1023.todo_ai.application.usecase

import com.github.koroku1023.todo_ai.domain.model.Task
import com.github.koroku1023.todo_ai.domain.repository.TaskRepository

class FakeTaskRepository : TaskRepository {
  private val store = mutableListOf<Task>()

  override fun findAll(): List<Task> = store.toList()

  override fun findByProjectId(projectId: String): List<Task> =
    store.filter { it.projectId == projectId }

  override fun findById(id: String): Task? = store.find { it.id == id }

  override fun save(task: Task): Task {
    store.removeAll { it.id == task.id }
    store.add(task)
    return task
  }

  override fun delete(task: Task) {
    store.removeAll { it.id == task.id }
  }
}
