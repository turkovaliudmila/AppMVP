package ru.geekbrains.appmvp

import android.app.Application
import ru.geekbrains.appmvp.model.di.AppComponent
import ru.geekbrains.appmvp.model.di.AppModule
import ru.geekbrains.appmvp.model.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}