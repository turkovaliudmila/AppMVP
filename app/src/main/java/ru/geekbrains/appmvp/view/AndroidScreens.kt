package ru.geekbrains.appmvp.view

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.appmvp.model.GithubUser

object AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(githubUser: GithubUser) = FragmentScreen { UserFragment.newInstance(githubUser) }
}