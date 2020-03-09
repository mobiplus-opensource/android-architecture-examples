package com.example.basearch.presentation.ui.pullRequest.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.BaseErrorStatus
import br.com.mobiplus.gitclient.domain.model.PullRequestModel
import br.com.mobiplus.gitclient.domain.usecases.GetPullRequestUseCase
import com.example.basearch.presentation.extensions.loadViewState
import com.example.basearch.presentation.ui.base.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PullRequestViewModel(private val getPullRequestUseCase: GetPullRequestUseCase) : ViewModel() {
    private val _pullRequestState =
        MutableLiveData<ViewState<PullRequestModel, BaseErrorData<BaseErrorStatus>>>()
    val pullRequestState: LiveData<ViewState<PullRequestModel, BaseErrorData<BaseErrorStatus>>>
        get() = _pullRequestState

    fun getPullRequest(owner: String, gitRepoName: String, pullRequestNumber: Long) {
        _pullRequestState.postValue(ViewState.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            val params = GetPullRequestUseCase.Params(
                owner = owner,
                gitRepoName = gitRepoName,
                pullRequestNumber = pullRequestNumber
            )
            val resultWrapper = getPullRequestUseCase.runAsync(params)

            val viewState = loadViewState(resultWrapper)
            _pullRequestState.postValue(viewState)
        }
    }
}