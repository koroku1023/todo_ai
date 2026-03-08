package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class UpdateProjectUseCaseTest {

  private val repository = FakeProjectRepository()
  private val useCase = UpdateProjectUseCase(repository)

  private val projectId = "550e8400-e29b-41d4-a716-446655440000"
  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testExecute() {
    val project = com.github.koroku1023.todo_ai.domain.model.Project.create(projectId, "プロジェクト", now)
    repository.save(project)

    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val update = ProjectUpdate(title = "新しいプロジェクト", color = "#FF0000", isFavorite = true)
    val result = useCase.execute(projectId, update, updatedNow)

    assertEquals("新しいプロジェクト", result.title)
    assertEquals("#FF0000", result.color)
    assertTrue(result.isFavorite)
    assertEquals(updatedNow, result.updatedAt)
  }

  @Test
  fun testExecuteThrowsWhenNotFound() {
    assertFailsWith<IllegalArgumentException> {
      useCase.execute("not-exist", ProjectUpdate("タイトル", null, false), now)
    }
  }
}
