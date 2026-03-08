package com.github.koroku1023.todo_ai.application.usecase

import com.github.koroku1023.todo_ai.domain.model.SubTask
import com.github.koroku1023.todo_ai.domain.repository.SubTaskRepository

class FakeSubTaskRepository : SubTaskRepository {
  private val store = mutableListOf<SubTask>()

  override fun findAll(): List<SubTask> = store.toList()

  override fun findByTaskId(taskId: String): List<SubTask> =
    store.filter { it.taskId == taskId }

  override fun findById(id: String): SubTask? = store.find { it.id == id }

  override fun save(subTask: SubTask): SubTask {
    store.removeAll { it.id == subTask.id }
    store.add(subTask)
    return subTask
  }

  override fun delete(subTask: SubTask) {
    store.removeAll { it.id == subTask.id }
  }
}
