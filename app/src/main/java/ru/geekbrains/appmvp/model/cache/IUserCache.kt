package ru.geekbrains.appmvp.model.cache

import ru.geekbrains.appmvp.model.storage.RoomGithubUser

interface IUserCache {
    fun insertUsers(users: List<RoomGithubUser>)
    fun getAllUsers(): List<RoomGithubUser>
}