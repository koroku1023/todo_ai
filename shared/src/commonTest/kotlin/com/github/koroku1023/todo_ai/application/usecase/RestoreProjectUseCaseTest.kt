package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull

class RestoreProjectUseCaseTest {

  private val repository = FakeProjectRepository()
  private val useCase = RestoreProjectUseCase(repository)

  private val projectId = "550e8400-e29b-41d4-a716-446655440000"
  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testExecute() {
    val project = com.github.koroku1023.todo_ai.domain.model.Project.create(projectId, "プロジェクト", now)
      .markAsDeleted(now)
    repository.save(project)

    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = useCase.execute(projectId, updatedNow)

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
