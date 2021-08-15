package ru.geekbrains.appmvp.view

interface RepoItemView : IItemView {
    fun setFullName(text: String)
    fun setDescription(text: String)
}