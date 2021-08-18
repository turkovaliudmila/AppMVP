package ru.geekbrains.appmvp.view

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.appmvp.model.UserRepo

@StateStrategyType(SingleStateStrategy::class)
interface RepoView : MvpView {
    fun setRepoInfo(repoInfo: UserRepo)
}