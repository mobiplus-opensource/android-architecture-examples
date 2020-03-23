package br.com.mobiplus.gitclient.presentation.ui.gitRepo.adapter

import br.com.mobiplus.gitclient.presentation.ui.gitRepo.model.GitRepoUIModel

interface GitRepoAdapterListener {
    fun onItemClick(item: GitRepoUIModel)
}