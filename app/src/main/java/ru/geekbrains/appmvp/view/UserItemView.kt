package ru.geekbrains.appmvp.view

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}