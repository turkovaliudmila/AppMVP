package ru.geekbrains.appmvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.appmvp.App
import ru.geekbrains.appmvp.databinding.FragmentRepoBinding
import ru.geekbrains.appmvp.model.UserRepo
import ru.geekbrains.appmvp.presenter.RepoPresenter

class RepoFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {
    private val repoInfo: UserRepo? by lazy {
        arguments?.getParcelable<UserRepo>(BUNDLE_EXTRA)
    }

    private var vb: FragmentRepoBinding? = null
    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
            repoInfo,
            App.instance.router
        )
    }

    companion object {

        const val BUNDLE_EXTRA = "REPO_INFO"

        fun newInstance(repoInfo: UserRepo): Fragment {
            val fragment = RepoFragment()
            fragment.arguments = bundleOf(BUNDLE_EXTRA to repoInfo)
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentRepoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun setRepoInfo(repoInfo: UserRepo) {
        vb?.fullName?.text = repoInfo.fullName
        vb?.forksCount?.text = repoInfo.forksCount.toString()
    }

    override fun backPressed() = presenter.backPressed()

}