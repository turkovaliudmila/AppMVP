package ru.geekbrains.appmvp.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getUserRepos(@Url repos_url: String): Single<List<UserRepo>>

}