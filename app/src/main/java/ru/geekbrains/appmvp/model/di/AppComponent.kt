package ru.geekbrains.appmvp.model.di

import dagger.Component
import ru.geekbrains.appmvp.presenter.MainPresenter
import ru.geekbrains.appmvp.presenter.RepoPresenter
import ru.geekbrains.appmvp.presenter.ReposPresenter
import ru.geekbrains.appmvp.presenter.UsersPresenter
import ru.geekbrains.appmvp.view.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class,
        SchedulersModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repoPresenter: RepoPresenter)
    fun inject(reposPresenter: ReposPresenter)
}