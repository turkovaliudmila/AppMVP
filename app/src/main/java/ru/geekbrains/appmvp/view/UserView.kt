package ru.geekbrains.appmvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.appmvp.model.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView: MvpView {
    fun setUserInfo(githubUser: GithubUser)
}