package ru.geekbrains.appmvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import ru.geekbrains.appmvp.model.GithubUser
import ru.geekbrains.appmvp.model.IGithubUsersRepo
import ru.geekbrains.appmvp.model.shedulers.ISchedulers
import ru.geekbrains.appmvp.view.IScreens
import ru.geekbrains.appmvp.view.UserItemView
import ru.geekbrains.appmvp.view.UsersView
import javax.inject.Inject

class UsersPresenter() : MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var schedulers: ISchedulers

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
            router.navigateTo(screens.repos(userInfo))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(schedulers.main())
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
