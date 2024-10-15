package app.todolist.di

import app.todolist.domain.reminder.repository.ReminderRepository
import app.todolist.infrastructure.repositories.ReminderRepositoryImpl
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
    fun provideReminderRepository(): ReminderRepository {
        return ReminderRepositoryImpl()
    }
}
