package br.com.mobiplus.gitclient.domain.usecases

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.BaseErrorStatus
import br.com.mobiplus.gitclient.domain.base.ResultWrapper
import br.com.mobiplus.gitclient.domain.base.usecase.BaseAsyncUseCase
import br.com.mobiplus.gitclient.domain.model.PullRequestModel
import br.com.mobiplus.gitclient.domain.repository.PullRequestRepository
import br.com.mobiplus.gitclient.domain.usecases.GetPullRequestsUseCase.Params

class GetPullRequestsUseCase(
    private val pullRequestRepository: PullRequestRepository
) : BaseAsyncUseCase<ResultWrapper<List<PullRequestModel>, BaseErrorData<BaseErrorStatus>>, Params>() {

    override suspend fun runAsync(params: Params): ResultWrapper<List<PullRequestModel>, BaseErrorData<BaseErrorStatus>> {
        return pullRequestRepository.getPullRequests(
            owner = params.owner,
            gitRepoName = params.gitRepoName
        ).transformError { BaseErrorData(errorBody = BaseErrorStatus.DEFAULT_ERROR) }
    }

    data class Params(
        val owner: String,
        val gitRepoName: String
    )
}