package ru.geekbrains.appmvp.presenter

import ru.geekbrains.appmvp.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}