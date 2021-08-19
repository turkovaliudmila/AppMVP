package ru.geekbrains.appmvp

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.geekbrains.appmvp.model.storage.Database

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    //Временно до даггера положим это тут
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    //lateinit var db: RoomDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(applicationContext)
        //db = ru.geekbrains.appmvp.model.storage.Database.getInstance()
    }

}