package com.example.basearch.presentation.ui.main.adapter

import br.com.pebmed.domain.model.GitRepoModel

interface GitRepoAdapterListener {
    fun onItemClick(gitRepo: GitRepoModel)
}