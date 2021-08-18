package ru.geekbrains.appmvp.model

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}