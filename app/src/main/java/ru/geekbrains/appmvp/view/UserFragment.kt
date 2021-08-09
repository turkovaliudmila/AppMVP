package ru.geekbrains.appmvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.appmvp.App
import ru.geekbrains.appmvp.databinding.FragmentUserBinding
import ru.geekbrains.appmvp.model.GithubUser
import ru.geekbrains.appmvp.presenter.UserPresenter

class UserFragment(): MvpAppCompatFragment(), UserView, BackButtonListener {

    private val user: GithubUser? by lazy {
        arguments?.getParcelable<GithubUser>(BUNDLE_EXTRA)
    }

    private var vb: FragmentUserBinding? = null
    private val presenter: UserPresenter by moxyPresenter { UserPresenter(user, App.instance.router) }

    companion object {

        const val BUNDLE_EXTRA = "USER_INFO"

        fun newInstance(githubUser: GithubUser): Fragment {
            val fragment = UserFragment()
            fragment.arguments = bundleOf(BUNDLE_EXTRA to githubUser)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun setUserInfo(githubUser: GithubUser) {
        vb?.login?.text = githubUser.login
    }

    override fun backPressed() = presenter.backPressed()
}