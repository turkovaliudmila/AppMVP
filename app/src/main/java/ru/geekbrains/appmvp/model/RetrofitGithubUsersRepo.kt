package ru.geekbrains.appmvp.model

import io.reactivex.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource): IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getUserRepos(url: String) = api.getUserRepos(url).subscribeOn(Schedulers.io())
}