package ru.geekbrains.appmvp.model.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.appmvp.model.*
import ru.geekbrains.appmvp.model.cache.IRepositoriesCache
import ru.geekbrains.appmvp.model.cache.IUserCache
import ru.geekbrains.appmvp.model.network.INetworkStatus
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun reposRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRepositoriesCache
    ): IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}