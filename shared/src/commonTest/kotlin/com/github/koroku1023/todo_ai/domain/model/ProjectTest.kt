package com.github.koroku1023.todo_ai.domain.model

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class ProjectTest {

  private val id = "550e8400-e29b-41d4-a716-446655440000"
  private val title = "タイトル"
  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testCreate() {
    val project = Project.create(id, title, now)

    assertEquals(id, project.id)
    assertEquals(title, project.title)
    assertEquals(null, project.color)
    assertEquals(SyncStatus.PENDING, project.syncStatus)
    assertEquals(false, project.isFavorite)
    assertEquals(now, project.createdAt)
    assertEquals(now, project.updatedAt)
    assertEquals(null, project.completedAt)
    assertEquals(null, project.deletedAt)
    assertEquals(false, project.isCompleted)
    assertEquals(false, project.isDeleted)
  }

  @Test
  fun testCreateWithColor() {
    val project = Project.create(id, title, now, "#FF5733")

    assertEquals("#FF5733", project.color)
  }

  @Test
  fun testComplete() {
    val project = Project.create(id, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = project.complete(updatedNow)

    assertEquals(updatedNow, result.completedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(SyncStatus.PENDING, result.syncStatus)
    assertEquals(true, result.isCompleted)
  }

  @Test
  fun testUnComplete() {
    val project = Project.create(id, title, now).complete(now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = project.unComplete(updatedNow)

    assertEquals(null, result.completedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(false, result.isCompleted)
  }

  @Test
  fun testToggleFavorite() {
    val project = Project.create(id, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = project.toggleFavorite(updatedNow)

    assertEquals(true, result.isFavorite)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(SyncStatus.PENDING, result.syncStatus)
  }

  @Test
  fun testRename() {
    val project = Project.create(id, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = project.rename("新しいタイトル", updatedNow)

    assertEquals("新しいタイトル", result.title)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(SyncStatus.PENDING, result.syncStatus)
  }

  @Test
  fun testChangeColor() {
    val project = Project.create(id, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = project.changeColor("#00FF00", updatedNow)

    assertEquals("#00FF00", result.color)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(SyncStatus.PENDING, result.syncStatus)
  }

  @Test
  fun testChangeColorToNull() {
    val project = Project.create(id, title, now, "#FF0000")
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = project.changeColor(null, updatedNow)

    assertEquals(null, result.color)
    assertEquals(updatedNow, result.updatedAt)
  }

  @Test
  fun testMarkAsDeleted() {
    val project = Project.create(id, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = project.markAsDeleted(updatedNow)

    assertEquals(updatedNow, result.deletedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(true, result.isDeleted)
  }

  @Test
  fun testRestore() {
    val project = Project.create(id, title, now).markAsDeleted(now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = project.restore(updatedNow)

    assertEquals(null, result.deletedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(false, result.isDeleted)
  }
}
