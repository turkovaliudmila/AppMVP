package ru.geekbrains.appmvp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.appmvp.databinding.ActivityMainBinding
import ru.geekbrains.appmvp.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainView {
    private var vb: ActivityMainBinding? = null
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener(
            View.OnClickListener {
                presenter.counter1Click()
            }
        )

        vb?.btnCounter2?.setOnClickListener(
            View.OnClickListener {
                presenter.counter2Click()
            }
        )

        vb?.btnCounter3?.setOnClickListener(
            View.OnClickListener {
                presenter.counter3Click()
            }
        )
    }

    override fun setButton1Text(text: String) {
        vb?.btnCounter1?.text = text
    }

    override fun setButton2Text(text: String) {
        vb?.btnCounter2?.text = text
    }

    override fun setButton3Text(text: String) {
        vb?.btnCounter3?.text = text
    }

}