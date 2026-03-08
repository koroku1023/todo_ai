package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class FetchSubTasksUseCaseTest {

  private val repository = FakeSubTaskRepository()
  private val useCase = FetchSubTasksUseCase(repository)

  private val taskId = "660e8400-e29b-41d4-a716-446655440001"
  private val otherTaskId = "660e8400-e29b-41d4-a716-446655440002"
  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testExecute() {
    val subTask1 = com.github.koroku1023.todo_ai.domain.model.SubTask.create("id-1", taskId, "サブタスク1", now)
    val subTask2 = com.github.koroku1023.todo_ai.domain.model.SubTask.create("id-2", taskId, "サブタスク2", now)
    val other = com.github.koroku1023.todo_ai.domain.model.SubTask.create("id-3", otherTaskId, "別タスクのサブタスク", now)
    repository.save(subTask1)
    repository.save(subTask2)
    repository.save(other)

    val result = useCase.execute(taskId)

    assertEquals(2, result.size)
    assertEquals(setOf("id-1", "id-2"), result.map { it.id }.toSet())
  }

  @Test
  fun testExecuteReturnsEmptyWhenNone() {
    val result = useCase.execute(taskId)
    assertEquals(0, result.size)
  }
}
