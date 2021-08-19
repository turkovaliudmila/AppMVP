package ru.geekbrains.appmvp.model.cache

import ru.geekbrains.appmvp.model.storage.Database
import ru.geekbrains.appmvp.model.storage.RoomGithubUser

class RoomUsersCache(private val db: Database) : IUserCache {
    override fun insertUsers(users: List<RoomGithubUser>) {
        db.userDao.insert(users)
    }

    override fun getAllUsers(): List<RoomGithubUser> {
        return db.userDao.getAll()
    }
}