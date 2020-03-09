package com.example.basearch.presentation.ui.main.adapter

import br.com.pebmed.domain.model.RepoModel

interface ReposAdapterListener {
    fun onItemClick(repo: RepoModel)
}