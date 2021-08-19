package ru.geekbrains.appmvp.view

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.appmvp.model.GithubUser
import ru.geekbrains.appmvp.model.UserRepo

interface IScreens {
    fun users() : Screen
    fun repos(user: GithubUser) : Screen
    fun repo(repo: UserRepo) : Screen
    fun user(githubUser: GithubUser) : Screen
}