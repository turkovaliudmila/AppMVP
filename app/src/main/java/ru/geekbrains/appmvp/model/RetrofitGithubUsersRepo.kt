package ru.geekbrains.appmvp.model

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.geekbrains.appmvp.model.cache.IRepositoriesCache
import ru.geekbrains.appmvp.model.cache.IUserCache
import ru.geekbrains.appmvp.model.network.INetworkStatus
import ru.geekbrains.appmvp.model.storage.Database
import ru.geekbrains.appmvp.model.storage.RoomGithubUser

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IUserCache
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(
                                user.id ?: "",
                                user.login ?: "",
                                user.avatarUrl ?: "",
                                user.reposUrl ?: ""
                            )
                        }
                        cache.insertUsers(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                cache.getAllUsers().map { roomUser ->
                    GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}