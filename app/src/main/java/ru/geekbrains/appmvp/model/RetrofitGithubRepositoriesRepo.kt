package ru.geekbrains.appmvp.model

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.geekbrains.appmvp.model.cache.IRepositoriesCache
import ru.geekbrains.appmvp.model.network.INetworkStatus
import ru.geekbrains.appmvp.model.storage.RoomGithubRepository

class RetrofitGithubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IRepositoriesCache
) : IGithubRepositoriesRepo {
    override fun getUserRepos(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getUserRepos(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomUser = user.login?.let { cache.findByLogin(it) }
                                    ?: throw RuntimeException("No such user in cache")
                                val roomRepos = repositories.map {
                                    RoomGithubRepository(
                                        it.id ?: "",
                                        it.fullName ?: "",
                                        it.description ?: "",
                                        it.forksCount ?: 0,
                                        user.id ?: ""
                                    )
                                }
                                cache.insertRepos(roomRepos)
                                repositories
                            }
                        }
                } ?: Single.error<List<UserRepo>>(RuntimeException("User has no repos url"))
                    .subscribeOn(
                        Schedulers.io()
                    )
            } else {
                Single.fromCallable {
                    val roomUser = user.login?.let { cache.findByLogin(it) }
                        ?: throw RuntimeException("No such user in cache")
                    cache.findReposByUser(roomUser.id)
                        .map { UserRepo(it.id, it.fullName, it.description, it.forksCount) }
                }

            }
        }.subscribeOn(Schedulers.io())
}