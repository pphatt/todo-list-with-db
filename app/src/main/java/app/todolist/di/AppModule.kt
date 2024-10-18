package app.todolist.di

import android.app.Application
import androidx.room.Room
import app.todolist.domain.todo.repository.TodoRepository
import app.todolist.infrastructure.database.TodoDao
import app.todolist.infrastructure.database.TodoDatabaseModule
import app.todolist.infrastructure.repositories.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabaseModule {
        return Room.databaseBuilder(
            app,
            TodoDatabaseModule::class.java,
            TodoDatabaseModule.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabaseModule): TodoRepository {
        return TodoRepositoryImpl(db.todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoDao(db: TodoDatabaseModule): TodoDao {
        return db.todoDao
    }
}
