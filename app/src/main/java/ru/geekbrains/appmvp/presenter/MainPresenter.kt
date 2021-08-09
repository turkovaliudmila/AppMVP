package ru.geekbrains.appmvp.presenter

import ru.geekbrains.appmvp.view.MainView
import ru.geekbrains.appmvp.model.CountersModel

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counter1Click() {
        counterClick(0)
    }

    fun counter2Click() {
        counterClick(1)
    }

    fun counter3Click() {
        counterClick(2)
    }

    fun counterClick(index: Int) {
        val nextValue = model.next(index)
        when (index) {
            0 -> view.setButton1Text(nextValue.toString())
            1 -> view.setButton2Text(nextValue.toString())
            2 -> view.setButton3Text(nextValue.toString())
        }
    }

}