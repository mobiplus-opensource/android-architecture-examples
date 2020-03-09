package com.example.basearch.presentation.ui.main.adapter

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.mobiplus.gitclient.domain.model.GitRepoModel
import com.bumptech.glide.Glide
import com.example.basearch.R
import com.example.basearch.presentation.extensions.isValidForGlide
import kotlinx.android.synthetic.main.item_repo_list.view.*

class GitRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(activity: Activity, gitRepo: GitRepoModel, listener: GitRepoAdapterListener) {
        itemView.textRepoName.text = gitRepo.name
        itemView.textRepoAuthor.text = gitRepo.ownerModel.login
        itemView.textRepoDescription.text = gitRepo.description
        itemView.textStarsCount.text = gitRepo.stargazersCount.toString()
        itemView.textForksCount.text = gitRepo.forksCount.toString()
        itemView.textIssuesCount.text = gitRepo.openIssuesCount.toString()

        if (activity.isValidForGlide()) {
            Glide.with(activity)
                .load(gitRepo.ownerModel.avatarUrl)
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(itemView.imageRepoAuthor)
        }

        itemView.setOnClickListener {
            listener.onItemClick(gitRepo)
        }
    }
}