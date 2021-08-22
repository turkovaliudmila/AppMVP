package ru.geekbrains.appmvp.model.cache

import ru.geekbrains.appmvp.model.storage.Database
import ru.geekbrains.appmvp.model.storage.RoomGithubRepository
import ru.geekbrains.appmvp.model.storage.RoomGithubUser

class RoomRepositoriesCache(private val db: Database) : IRepositoriesCache {
    override fun findByLogin(userLogin: String): RoomGithubUser? {
        return db.userDao.findByLogin(userLogin)
    }

    override fun insertRepos(repos: List<RoomGithubRepository>) {
        db.repositoryDao.insert(repos)
    }

    override fun findReposByUser(userId: String): List<RoomGithubRepository> {
        return db.repositoryDao.findForUser(userId)
    }
}