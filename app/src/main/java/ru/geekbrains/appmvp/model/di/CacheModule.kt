package ru.geekbrains.appmvp.model.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.appmvp.App
import ru.geekbrains.appmvp.model.cache.IRepositoriesCache
import ru.geekbrains.appmvp.model.cache.IUserCache
import ru.geekbrains.appmvp.model.cache.RoomRepositoriesCache
import ru.geekbrains.appmvp.model.cache.RoomUsersCache
import ru.geekbrains.appmvp.model.storage.Database
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()


    @Singleton
    @Provides
    fun usersCache(database: Database): IUserCache = RoomUsersCache(database)

    @Singleton
    @Provides
    fun reposCache(database: Database): IRepositoriesCache = RoomRepositoriesCache(database)
}