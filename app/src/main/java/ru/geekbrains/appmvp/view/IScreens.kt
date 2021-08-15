package ru.geekbrains.appmvp.view

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.appmvp.model.GithubUser
import ru.geekbrains.appmvp.model.UserRepo

interface IScreens {
    fun users() : Screen
    fun repos(repo_url: String) : Screen
    fun repo(repo: UserRepo) : Screen
    fun user(githubUser: GithubUser) : Screen
}