package com.github.koroku1023.todo_ai.domain.model

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskTest {

  private val id = "660e8400-e29b-41d4-a716-446655440001"
  private val projectId = "550e8400-e29b-41d4-a716-446655440000"
  private val title = "タスク"
  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testCreate() {
    val task = Task.create(id, projectId, title, now)

    assertEquals(id, task.id)
    assertEquals(projectId, task.projectId)
    assertEquals(title, task.title)
    assertEquals(null, task.memo)
    assertEquals(null, task.color)
    assertEquals(SyncStatus.PENDING, task.syncStatus)
    assertEquals(now, task.createdAt)
    assertEquals(now, task.updatedAt)
    assertEquals(null, task.completedAt)
    assertEquals(null, task.deletedAt)
    assertEquals(false, task.isCompleted)
    assertEquals(false, task.isDeleted)
  }

  @Test
  fun testCreateWithColor() {
    val task = Task.create(id, projectId, title, now, "#FF5733")

    assertEquals("#FF5733", task.color)
  }

  @Test
  fun testComplete() {
    val task = Task.create(id, projectId, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = task.complete(updatedNow)

    assertEquals(updatedNow, result.completedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(SyncStatus.PENDING, result.syncStatus)
    assertEquals(true, result.isCompleted)
  }

  @Test
  fun testUnComplete() {
    val task = Task.create(id, projectId, title, now).complete(now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = task.unComplete(updatedNow)

    assertEquals(null, result.completedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(false, result.isCompleted)
  }

  @Test
  fun testUpdate() {
    val task = Task.create(id, projectId, title, now, "#FF0000")
    val newProjectId = "770e8400-e29b-41d4-a716-446655440002"
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = task.update("新しいタスク", null, "#00FF00", newProjectId, updatedNow)

    assertEquals("新しいタスク", result.title)
    assertEquals(null, result.memo)
    assertEquals("#00FF00", result.color)
    assertEquals(newProjectId, result.projectId)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(SyncStatus.PENDING, result.syncStatus)
  }

  @Test
  fun testUpdateColorToNull() {
    val task = Task.create(id, projectId, title, now, "#FF0000")
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = task.update(title, null, null, projectId, updatedNow)

    assertEquals(null, result.color)
    assertEquals(updatedNow, result.updatedAt)
  }

  @Test
  fun testUpdateWithMemo() {
    val task = Task.create(id, projectId, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = task.update(title, "メモ内容", null, projectId, updatedNow)

    assertEquals("メモ内容", result.memo)
    assertEquals(updatedNow, result.updatedAt)
  }

  @Test
  fun testUpdateMemoToNull() {
    val task = Task.create(id, projectId, title, now)
    val withMemo = task.update(title, "メモ内容", null, projectId, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = withMemo.update(title, null, null, projectId, updatedNow)

    assertEquals(null, result.memo)
    assertEquals(updatedNow, result.updatedAt)
  }

  @Test
  fun testMarkAsDeleted() {
    val task = Task.create(id, projectId, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = task.markAsDeleted(updatedNow)

    assertEquals(updatedNow, result.deletedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(true, result.isDeleted)
  }

  @Test
  fun testRestore() {
    val task = Task.create(id, projectId, title, now).markAsDeleted(now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = task.restore(updatedNow)

    assertEquals(null, result.deletedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(false, result.isDeleted)
  }
}
