package com.example.basearch.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pebmed.domain.base.BaseErrorData
import br.com.pebmed.domain.base.BaseErrorStatus
import br.com.pebmed.domain.model.GitRepoModel
import br.com.pebmed.domain.usecases.GetGitRepoListUseCase
import com.example.basearch.presentation.extensions.loadViewState
import com.example.basearch.presentation.ui.base.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val getGitRepoListUseCase: GetGitRepoListUseCase) : ViewModel() {
    private val _gitRepoListState =
        MutableLiveData<ViewState<List<GitRepoModel>, BaseErrorData<BaseErrorStatus>>>()
    val gitRepoListState: LiveData<ViewState<List<GitRepoModel>, BaseErrorData<BaseErrorStatus>>>
        get() = _gitRepoListState

    init {
        loadGitRepoList()
    }

    fun loadGitRepoList() {
        _gitRepoListState.postValue(ViewState.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            val params = GetGitRepoListUseCase.Params(true)
            val resultWrapper = getGitRepoListUseCase.runAsync(params)

            val viewState = loadViewState(resultWrapper)
            _gitRepoListState.postValue(viewState)
        }
    }
}