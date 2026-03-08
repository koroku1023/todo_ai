package com.github.koroku1023.todo_ai.application.usecase

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class FetchProjectsUseCaseTest {

  private val repository = FakeProjectRepository()
  private val useCase = FetchProjectsUseCase(repository)

  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testExecute() {
    val project1 = com.github.koroku1023.todo_ai.domain.model.Project.create("id-1", "プロジェクト1", now)
    val project2 = com.github.koroku1023.todo_ai.domain.model.Project.create("id-2", "プロジェクト2", now)
    repository.save(project1)
    repository.save(project2)

    val result = useCase.execute()

    assertEquals(2, result.size)
  }

  @Test
  fun testExecuteReturnsEmptyWhenNone() {
    val result = useCase.execute()
    assertEquals(0, result.size)
  }
}
