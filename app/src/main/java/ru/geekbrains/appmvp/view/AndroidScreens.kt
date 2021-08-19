package ru.geekbrains.appmvp.view

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.appmvp.model.GithubUser
import ru.geekbrains.appmvp.model.UserRepo

object AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun repos(user: GithubUser) = FragmentScreen { ReposFragment.newInstance(user) }
    override fun repo(repo: UserRepo) = FragmentScreen { RepoFragment.newInstance(repo) }
    override fun user(githubUser: GithubUser) =
        FragmentScreen { UserFragment.newInstance(githubUser) }
}