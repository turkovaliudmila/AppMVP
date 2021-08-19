package ru.geekbrains.appmvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import ru.geekbrains.appmvp.model.GithubUser
import ru.geekbrains.appmvp.model.IGithubRepositoriesRepo
import ru.geekbrains.appmvp.model.UserRepo
import ru.geekbrains.appmvp.view.AndroidScreens
import ru.geekbrains.appmvp.view.RepoItemView
import ru.geekbrains.appmvp.view.ReposView

class ReposPresenter(
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubRepositoriesRepo,
    private val user: GithubUser,
    private val router: Router
) : MvpPresenter<ReposView>() {

    private val disposables = CompositeDisposable()

    class ReposListPresenter : IRepoListPresenter {
        val repos = mutableListOf<UserRepo>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            repo.fullName?.let { view.setFullName(it) }
            repo.description?.let { view.setDescription(it) }
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        reposListPresenter.itemClickListener = { itemView ->
            val repoInfo = reposListPresenter.repos[itemView.pos]
            router.navigateTo(AndroidScreens.repo(repoInfo))
        }
    }

    private fun loadData() {
        usersRepo.getUserRepos(user)
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                reposListPresenter.repos.clear()
                reposListPresenter.repos.addAll(repos)
                viewState.updateList()
            }, viewState::showError
            )
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