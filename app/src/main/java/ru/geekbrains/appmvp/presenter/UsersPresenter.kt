package ru.geekbrains.appmvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import ru.geekbrains.appmvp.model.GithubUser
import ru.geekbrains.appmvp.model.IGithubUsersRepo
import ru.geekbrains.appmvp.view.AndroidScreens
import ru.geekbrains.appmvp.view.UserItemView
import ru.geekbrains.appmvp.view.UsersView

class UsersPresenter(
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
    private val router: Router
) : MvpPresenter<UsersView>() {

    private val disposables = CompositeDisposable()

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val userInfo = usersListPresenter.users[itemView.pos]
            router.navigateTo(AndroidScreens.repos(userInfo.repos_url!!))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, viewState::showError)
            .addTo(disposables)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
