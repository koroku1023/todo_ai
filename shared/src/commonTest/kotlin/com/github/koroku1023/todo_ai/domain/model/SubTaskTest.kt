package com.github.koroku1023.todo_ai.domain.model

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class SubTaskTest {

  private val id = "770e8400-e29b-41d4-a716-446655440003"
  private val taskId = "660e8400-e29b-41d4-a716-446655440001"
  private val title = "サブタスク"
  private val now = Instant.fromEpochMilliseconds(0)

  @Test
  fun testCreate() {
    val subTask = SubTask.create(id, taskId, title, now)

    assertEquals(id, subTask.id)
    assertEquals(taskId, subTask.taskId)
    assertEquals(title, subTask.title)
    assertEquals(null, subTask.memo)
    assertEquals(null, subTask.startAt)
    assertEquals(null, subTask.endAt)
    assertEquals(null, subTask.repeatDays)
    assertEquals(SyncStatus.PENDING, subTask.syncStatus)
    assertEquals(now, subTask.createdAt)
    assertEquals(now, subTask.updatedAt)
    assertEquals(null, subTask.completedAt)
    assertEquals(null, subTask.deletedAt)
    assertEquals(false, subTask.isCompleted)
    assertEquals(false, subTask.isDeleted)
  }

  @Test
  fun testCreateWithOptionalParams() {
    val memo = "メモ"
    val startAt = Instant.fromEpochMilliseconds(1000)
    val endAt = Instant.fromEpochMilliseconds(2000)
    val subTask = SubTask.create(id, taskId, title, now, memo, startAt, endAt, "Mon,Fri")

    assertEquals(memo, subTask.memo)
    assertEquals(startAt, subTask.startAt)
    assertEquals(endAt, subTask.endAt)
    assertEquals("Mon,Fri", subTask.repeatDays)
  }

  @Test
  fun testComplete() {
    val subTask = SubTask.create(id, taskId, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = subTask.complete(updatedNow)

    assertEquals(updatedNow, result.completedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(SyncStatus.PENDING, result.syncStatus)
    assertEquals(true, result.isCompleted)
  }

  @Test
  fun testUnComplete() {
    val subTask = SubTask.create(id, taskId, title, now).complete(now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = subTask.unComplete(updatedNow)

    assertEquals(null, result.completedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(false, result.isCompleted)
  }

  @Test
  fun testUpdateDetails() {
    val subTask = SubTask.create(id, taskId, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val newTitle = "新しいタイトル"
    val newMemo = "新しいメモ"
    val newStartAt = Instant.fromEpochMilliseconds(2000)
    val newEndAt = Instant.fromEpochMilliseconds(3000)
    val newRepeatDays = "Tue,Thu"
    val result = subTask.updateDetails(newTitle, newMemo, newStartAt, newEndAt, newRepeatDays, updatedNow)

    assertEquals(newTitle, result.title)
    assertEquals(newMemo, result.memo)
    assertEquals(newStartAt, result.startAt)
    assertEquals(newEndAt, result.endAt)
    assertEquals(newRepeatDays, result.repeatDays)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(SyncStatus.PENDING, result.syncStatus)
  }

  @Test
  fun testSetSchedule() {
    val subTask = SubTask.create(id, taskId, title, now)
    val startAt = Instant.fromEpochMilliseconds(1000)
    val endAt = Instant.fromEpochMilliseconds(2000)
    val updatedNow = Instant.fromEpochMilliseconds(3000)
    val result = subTask.setSchedule(startAt, endAt, updatedNow)

    assertEquals(startAt, result.startAt)
    assertEquals(endAt, result.endAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(SyncStatus.PENDING, result.syncStatus)
  }

  @Test
  fun testMarkAsDeleted() {
    val subTask = SubTask.create(id, taskId, title, now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = subTask.markAsDeleted(updatedNow)

    assertEquals(updatedNow, result.deletedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(true, result.isDeleted)
  }

  @Test
  fun testRestore() {
    val subTask = SubTask.create(id, taskId, title, now).markAsDeleted(now)
    val updatedNow = Instant.fromEpochMilliseconds(1000)
    val result = subTask.restore(updatedNow)

    assertEquals(null, result.deletedAt)
    assertEquals(updatedNow, result.updatedAt)
    assertEquals(false, result.isDeleted)
  }
}
