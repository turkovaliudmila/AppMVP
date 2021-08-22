package ru.geekbrains.appmvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}