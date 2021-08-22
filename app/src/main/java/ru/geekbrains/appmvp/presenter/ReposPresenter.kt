package ru.geekbrains.appmvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter
import ru.geekbrains.appmvp.model.GithubUser
import ru.geekbrains.appmvp.model.IGithubRepositoriesRepo
import ru.geekbrains.appmvp.model.UserRepo
import ru.geekbrains.appmvp.model.shedulers.ISchedulers
import ru.geekbrains.appmvp.view.IScreens
import ru.geekbrains.appmvp.view.RepoItemView
import ru.geekbrains.appmvp.view.ReposView
import javax.inject.Inject

class ReposPresenter(private val user: GithubUser) : MvpPresenter<ReposView>() {

    @Inject
    lateinit var reposRepo: IGithubRepositoriesRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var schedulers: ISchedulers

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
            router.navigateTo(screens.repo(repoInfo))
        }
    }

    private fun loadData() {
        reposRepo.getUserRepos(user)
            .observeOn(schedulers.main())
            .subscribe(
                { repos ->
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