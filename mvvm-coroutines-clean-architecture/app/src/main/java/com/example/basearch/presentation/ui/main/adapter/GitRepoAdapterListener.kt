package com.example.basearch.presentation.ui.main.adapter

import br.com.mobiplus.gitclient.domain.model.GitRepoModel

interface GitRepoAdapterListener {
    fun onItemClick(gitRepo: GitRepoModel)
}