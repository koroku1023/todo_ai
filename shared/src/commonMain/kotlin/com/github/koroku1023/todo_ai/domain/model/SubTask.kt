package com.github.koroku1023.todo_ai.domain.model

import kotlinx.datetime.Instant

data class SubTask(
  val id: String,
  val taskId: String,
  val title: String,
  val memo: String?,
  val startAt: Instant?,
  val endAt: Instant?,
  val repeatDays: String?,
  val syncStatus: SyncStatus,
  val completedAt: Instant?,
  val createdAt: Instant,
  val updatedAt: Instant,
  val deletedAt: Instant?,
) {
  companion object {
    fun create(
      id: String,
      taskId: String,
      title: String,
      now: Instant,
      memo: String? = null,
      startAt: Instant? = null,
      endAt: Instant? = null,
      repeatDays: String? = null
    ): SubTask {
      return SubTask(
        id = id,
        taskId = taskId,
        title = title,
        memo = memo,
        startAt = startAt,
        endAt = endAt,
        repeatDays = repeatDays,
        syncStatus = SyncStatus.PENDING,
        completedAt = null,
        createdAt = now,
        updatedAt = now,
        deletedAt = null,
      )
    }
  }

  val isCompleted: Boolean get() = completedAt != null
  val isDeleted: Boolean get() = deletedAt != null

  fun complete(now: Instant): SubTask {
    return copy(completedAt = now, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun unComplete(now: Instant): SubTask {
    return copy(completedAt = null, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun updateDetails(
    title: String,
    memo: String?,
    startAt: Instant?,
    endAt: Instant?,
    repeatDays: String?,
    now: Instant
  ): SubTask {
    return copy(
      title = title,
      memo = memo,
      startAt = startAt,
      endAt = endAt,
      repeatDays = repeatDays,
      updatedAt = now,
      syncStatus = SyncStatus.PENDING
    )
  }

  fun setSchedule(startAt: Instant, endAt: Instant, now: Instant): SubTask {
    return copy(
      startAt = startAt,
      endAt = endAt,
      updatedAt = now,
      syncStatus = SyncStatus.PENDING
    )
  }

  fun markAsDeleted(now: Instant): SubTask {
    return copy(deletedAt = now, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun restore(now: Instant): SubTask {
    return copy(deletedAt = null, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }
}
