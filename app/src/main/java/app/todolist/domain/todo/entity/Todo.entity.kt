package app.todolist.domain.todo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "dueDate")
    val dueDate: Long? = null,

    @ColumnInfo(name = "status")
    val status: Boolean = false,

    @ColumnInfo(name = "createdAt")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "deletedAt")
    val deletedAt: Long? = null
)

class InvalidNoteException(message: String) : Exception(message)
