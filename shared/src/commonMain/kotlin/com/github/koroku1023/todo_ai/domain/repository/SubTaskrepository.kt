package com.github.koroku1023.todo_ai.domain.repository

import com.github.koroku1023.todo_ai.domain.model.SubTask

interface SubTaskRepository {
  fun findAll(): List<SubTask>
  fun findByTaskId(taskId: String): List<SubTask>
  fun findById(id: String): SubTask?
  fun save(subTask: SubTask): SubTask
  fun delete(subTask: SubTask)
}
