package br.com.mobiplus.gitclient.presentation.ui.main.adapter

import br.com.mobiplus.gitclient.presentation.ui.main.model.GitRepoUIModel

interface GitRepoAdapterListener {
    fun onItemClick(item: GitRepoUIModel)
}