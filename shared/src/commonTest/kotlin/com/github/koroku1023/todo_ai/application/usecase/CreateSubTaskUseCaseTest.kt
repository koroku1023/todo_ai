package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CreateSubTaskUseCaseTest {

  private val repository = FakeSubTaskRepository()
  private val useCase = CreateSubTaskUseCase(repository)

  private val taskId = "660e8400-e29b-41d4-a716-446655440001"
  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testExecute() {
    val params = SubTaskCreate(
      taskId = taskId,
      title = "サブタスク",
      memo = null,
      startAt = null,
      endAt = null,
      repeatDays = null,
    )
    val result = useCase.execute(params, now)

    assertEquals(taskId, result.taskId)
    assertEquals("サブタスク", result.title)
    assertNull(result.memo)
    assertNull(result.completedAt)
    assertNull(result.deletedAt)
    assertEquals(now, result.createdAt)
  }

  @Test
  fun testExecuteWithOptionalParams() {
    val params = SubTaskCreate(
      taskId = taskId,
      title = "サブタスク",
      memo = "メモ",
      startAt = Instant.fromEpochMilliseconds(1000),
      endAt = Instant.fromEpochMilliseconds(2000),
      repeatDays = listOf("Mon", "Fri"),
    )
    val result = useCase.execute(params, now)

    assertEquals("メモ", result.memo)
    assertEquals(listOf("Mon", "Fri"), result.repeatDays)
  }
}
