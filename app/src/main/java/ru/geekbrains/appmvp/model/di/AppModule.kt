package ru.geekbrains.appmvp.model.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.appmvp.App

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}