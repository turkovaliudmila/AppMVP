package ru.geekbrains.appmvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.appmvp.model.UserRepo
import ru.geekbrains.appmvp.view.RepoView
import javax.inject.Inject

class RepoPresenter(private val repoInfo: UserRepo?) :
    MvpPresenter<RepoView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repoInfo?.let { viewState.setRepoInfo(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}