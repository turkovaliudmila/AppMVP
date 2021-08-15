package ru.geekbrains.appmvp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.appmvp.databinding.ItemRepoBinding
import ru.geekbrains.appmvp.presenter.IRepoListPresenter

class ReposAdapter(val presenter: IRepoListPresenter) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root), RepoItemView {

        override var pos = -1

        override fun setFullName(text: String) = with(vb) {
            fullName.text = text
        }

        override fun setDescription(text: String)= with(vb) {
            description.text = text
        }


    }
}