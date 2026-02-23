package com.github.koroku1023.todo_ai.domain.model

import kotlinx.datetime.Instant

data class Task(
  val id: String,
  val projectId: String,
  val title: String,
  val color: String?,
  val syncStatus: SyncStatus,
  val createdAt: Instant,
  val updatedAt: Instant,
  val completedAt: Instant?,
  val deletedAt: Instant?,
) {

  companion object {
    fun create(id: String, projectId: String, title: String, now: Instant, color: String? = null): Task {
      return Task(
        id = id,
        projectId = projectId,
        title = title,
        color = color,
        syncStatus = SyncStatus.PENDING,
        createdAt = now,
        updatedAt = now,
        completedAt = null,
        deletedAt = null,
      )
    }
  }

  val isCompleted: Boolean get() = completedAt != null
  val isDeleted: Boolean get() = deletedAt != null

  fun complete(now: Instant): Task {
    return copy(completedAt = now, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun unComplete(now: Instant): Task {
    return copy(completedAt = null, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun rename(newTitle: String, now: Instant): Task {
    return copy(title = newTitle, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun changeColor(newColor: String?, now: Instant): Task {
    return copy(color = newColor, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun moveToProject(newProjectId: String, now: Instant): Task {
    return copy(projectId = newProjectId, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun markAsDeleted(now: Instant): Task {
    return copy(deletedAt = now, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }

  fun restore(now: Instant): Task {
    return copy(deletedAt = null, updatedAt = now, syncStatus = SyncStatus.PENDING)
  }
}
