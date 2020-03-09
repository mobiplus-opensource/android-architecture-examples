package br.com.mobiplus.gitclient.presentation.ui.pullRequest.list.adapter

interface PullRequestListAdapterListener {
    fun onItemClick(pullRequestNumber: Long)
}