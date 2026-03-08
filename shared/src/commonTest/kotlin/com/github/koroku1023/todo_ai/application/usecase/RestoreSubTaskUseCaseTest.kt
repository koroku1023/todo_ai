package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull

class RestoreSubTaskUseCaseTest {

  private val repository = FakeSubTaskRepository()
  private val useCase = RestoreSubTaskUseCase(repository)

  private val subTaskId = "770e8400-e29b-41d4-a716-446655440003"
  private val taskId = "660e8400-e29b-41d4-a716-446655440001"
  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testExecute() {
    val subTask = com.github.koroku1023.todo_ai.domain.model.SubTask.create(subTaskId, taskId, "サブタスク", now)
      .markAsDeleted(now)
    repository.save(subTask)

    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = useCase.execute(subTaskId, updatedNow)

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
