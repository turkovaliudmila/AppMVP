package ru.geekbrains.appmvp.model

import io.reactivex.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}