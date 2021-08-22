package ru.geekbrains.appmvp.model.cache

import ru.geekbrains.appmvp.model.storage.RoomGithubRepository
import ru.geekbrains.appmvp.model.storage.RoomGithubUser

interface IRepositoriesCache {
    fun findByLogin(userLogin: String): RoomGithubUser?
    fun insertRepos(repos: List<RoomGithubRepository>)
    fun findReposByUser(userId: String): List<RoomGithubRepository>
}