package br.com.mobiplus.gitclient.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.BaseErrorStatus
import br.com.mobiplus.gitclient.domain.model.GitRepoModel
import br.com.mobiplus.gitclient.domain.usecases.GetGitRepoListUseCase
import br.com.mobiplus.gitclient.presentation.extensions.loadViewState
import br.com.mobiplus.gitclient.presentation.ui.base.ViewState
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