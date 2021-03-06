package ru.geekbrains.appmvp.view

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface UsersView : MvpView {
    fun init()
    fun updateList()
    fun showError(error: Throwable)
}