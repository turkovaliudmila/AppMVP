package ru.geekbrains.appmvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import ru.geekbrains.appmvp.view.AndroidScreens
import ru.geekbrains.appmvp.model.GithubUser
import ru.geekbrains.appmvp.model.GithubUsersRepo
import ru.geekbrains.appmvp.view.UserItemView
import ru.geekbrains.appmvp.view.UsersView

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) : MvpPresenter<UsersView>() {

    private val disposables = CompositeDisposable()

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val userInfo = usersListPresenter.users[itemView.pos]
            router.navigateTo(AndroidScreens.user(userInfo))
        }
    }

    private fun loadData() {
        usersRepo
            .getUsers()
            .subscribe(
                { usersListPresenter.users.addAll(it) },
                viewState::showError
            )
            .addTo(disposables)

        viewState.updateList()
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
