package ru.geekbrains.appmvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.appmvp.model.GithubUser
import ru.geekbrains.appmvp.view.UserView

class UserPresenter(private val userInfo: GithubUser?, private val router: Router) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val userInfo: GithubUser = userInfo ?: GithubUser()
        viewState.setUserInfo(userInfo)

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}