package ru.geekbrains.appmvp.model

import io.reactivex.Single

interface IGithubRepositoriesRepo {
    fun getUserRepos(user: GithubUser): Single<List<UserRepo>>
}