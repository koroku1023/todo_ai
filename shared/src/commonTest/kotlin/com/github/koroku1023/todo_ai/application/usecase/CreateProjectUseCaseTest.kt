package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

class CreateProjectUseCaseTest {

  private val repository = FakeProjectRepository()
  private val useCase = CreateProjectUseCase(repository)

  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testExecute() {
    val result = useCase.execute("プロジェクト", now)

    assertEquals("プロジェクト", result.title)
    assertNull(result.color)
    assertFalse(result.isFavorite)
    assertFalse(result.isCompleted)
    assertFalse(result.isDeleted)
    assertEquals(now, result.createdAt)
  }
}
