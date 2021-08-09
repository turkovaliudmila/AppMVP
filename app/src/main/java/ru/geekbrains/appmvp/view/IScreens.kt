package ru.geekbrains.appmvp.view

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.appmvp.model.GithubUser

interface IScreens {
    fun users() : Screen
    fun user(githubUser: GithubUser) : Screen
}