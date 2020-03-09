package com.example.basearch.presentation.ui.main.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mobiplus.gitclient.domain.model.GitRepoModel
import com.example.basearch.R

class GitRepoListAdapter(
    private val activity: Activity,
    private val gitRepoList: MutableList<GitRepoModel>,
    private val listener: GitRepoAdapterListener
) : RecyclerView.Adapter<GitRepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo_list, parent, false)
        return GitRepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gitRepoList.count()
    }

    override fun onBindViewHolder(holder: GitRepoViewHolder, position: Int) {
        val repo = gitRepoList[position]

        holder.bindView(activity, repo, listener)
    }

    fun addItems(gitRepos: List<GitRepoModel>) {
        val oldSize = gitRepoList.size
        gitRepoList.addAll(gitRepos)
        notifyItemRangeInserted(oldSize, gitRepos.size)
    }

    fun addItem(gitRepo: GitRepoModel) {
        gitRepoList.add(gitRepo)
        notifyItemInserted(gitRepoList.size - 1)
    }

    fun isEmpty() = gitRepoList.isEmpty()

}