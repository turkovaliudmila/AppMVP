package ru.geekbrains.appmvp.model.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.appmvp.model.shedulers.DefaultSchedulers
import ru.geekbrains.appmvp.model.shedulers.ISchedulers
import javax.inject.Singleton

@Module
class SchedulersModule {
    @Singleton
    @Provides
    fun schedulers(): ISchedulers = DefaultSchedulers()
}