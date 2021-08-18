package ru.geekbrains.appmvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.appmvp.App
import ru.geekbrains.appmvp.databinding.FragmentReposBinding
import ru.geekbrains.appmvp.model.ApiHolder
import ru.geekbrains.appmvp.model.RetrofitGithubUsersRepo
import ru.geekbrains.appmvp.presenter.ReposPresenter

class ReposFragment : MvpAppCompatFragment(), ReposView, BackButtonListener {

    private val repo_url: String? by lazy {
        arguments?.getString(BUNDLE_EXTRA)
    }

    private var vb: FragmentReposBinding? = null
    private val presenter: ReposPresenter by moxyPresenter {
        ReposPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            repo_url!!,
            App.instance.router
        )
    }

    companion object {

        const val BUNDLE_EXTRA = "REPO_URL"

        fun newInstance(repo_url: String): Fragment {
            val fragment = ReposFragment()
            fragment.arguments = bundleOf(BUNDLE_EXTRA to repo_url)
            return fragment
        }
    }

    var adapter: ReposAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentReposBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.repos?.layoutManager = LinearLayoutManager(context)
        adapter = ReposAdapter(presenter.reposListPresenter)
        vb?.repos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun backPressed() = presenter.backPressed()
}