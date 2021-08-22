package ru.geekbrains.appmvp.model.shedulers

import io.reactivex.Scheduler

interface ISchedulers {
    fun background(): Scheduler
    fun main(): Scheduler
}