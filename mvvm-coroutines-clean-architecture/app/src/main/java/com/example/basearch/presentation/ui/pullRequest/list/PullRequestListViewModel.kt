package com.example.basearch.presentation.ui.pullRequest.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.BaseErrorStatus
import br.com.mobiplus.gitclient.domain.model.PullRequestModel
import br.com.mobiplus.gitclient.domain.usecases.GetPullRequestsUseCase
import com.example.basearch.presentation.extensions.loadViewState
import com.example.basearch.presentation.ui.base.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PullRequestListViewModel(
    private val getPullRequestsUseCase: GetPullRequestsUseCase
) : ViewModel() {
    private val _pullRequestListState =
        MutableLiveData<ViewState<List<PullRequestModel>, BaseErrorData<BaseErrorStatus>>>()
    val pullRequestListState: LiveData<ViewState<List<PullRequestModel>, BaseErrorData<BaseErrorStatus>>>
        get() = _pullRequestListState

    fun loadPullRequestList(owner: String, gitRepoName: String) {
        _pullRequestListState.postValue(ViewState.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            val params = GetPullRequestsUseCase.Params(owner, gitRepoName)
            val resultWrapper = getPullRequestsUseCase.runAsync(params)

            val viewState = loadViewState(resultWrapper)
            _pullRequestListState.postValue(viewState)
        }
    }
}