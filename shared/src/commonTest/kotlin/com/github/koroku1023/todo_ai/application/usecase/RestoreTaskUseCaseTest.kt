package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull

class RestoreTaskUseCaseTest {

  private val repository = FakeTaskRepository()
  private val useCase = RestoreTaskUseCase(repository)

  private val taskId = "660e8400-e29b-41d4-a716-446655440001"
  private val projectId = "550e8400-e29b-41d4-a716-446655440000"
  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testExecute() {
    val task = com.github.koroku1023.todo_ai.domain.model.Task.create(taskId, projectId, "タスク", now)
      .markAsDeleted(now)
    repository.save(task)

    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = useCase.execute(taskId, updatedNow)

    assertFalse(result.isDeleted)
    assertNull(result.deletedAt)
    assertEquals(updatedNow, result.updatedAt)
  }

  @Test
  fun testExecuteThrowsWhenNotFound() {
    assertFailsWith<IllegalArgumentException> {
      useCase.execute("not-exist", now)
    }
  }
}
