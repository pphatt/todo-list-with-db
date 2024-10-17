package app.todolist.di

import app.todolist.domain.todo.repository.TodoRepository
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
    fun provideTodoRepository(): TodoRepository {
        return TodoRepositoryImpl()
    }
}
