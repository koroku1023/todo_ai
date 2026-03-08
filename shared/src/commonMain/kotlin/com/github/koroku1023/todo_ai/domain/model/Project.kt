package com.github.koroku1023.todo_ai.domain.model

import kotlinx.datetime.Instant

enum class SyncStatus {
  PENDING,
  SYNCED,
}

data class Project(
  val id: String,
  val title: String,
  val color: String?,
  val syncStatus: SyncStatus,
  val isFavorite: Boolean,
  val createdAt: Instant,
  val updatedAt: Instant,
  val completedAt: Instant?,
  val deletedAt: Instant?,
) {

  companion object {
    fun create(id: String, title: String, now: Instant, color: String? = null): Project {
      return Project(
        id = id,
        title = title,
        color = color,
        syncStatus = SyncStatus.PENDING,
        isFavorite = false,
        createdAt = now,
        updatedAt = now,
        completedAt = null,
        deletedAt = null,
      )
    }
  }

  val isCompleted: Boolean get() = completedAt != null
  val isDeleted: Boolean get() = deletedAt != null

  fun complete(now: Instant): Project {
    return copy(completedAt = now, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun unComplete(now: Instant): Project {
    return copy(completedAt = null, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun markAsDeleted(now: Instant): Project {
    return copy(deletedAt = now, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun restore(now: Instant): Project {
    return copy(deletedAt = null, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun update(title: String, color: String?, isFavorite: Boolean, now: Instant): Project {
    return copy(
      title = title,
      color = color,
      isFavorite = isFavorite,
      updatedAt = now,
      syncStatus = SyncStatus.PENDING,
    )
  }
}
