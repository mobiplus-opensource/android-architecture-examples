package br.com.mobiplus.gitclient.domain.usecases

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.ResultCode
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.base.usecase.BaseAsyncUseCase
import br.com.mobiplus.gitclient.domain.model.PullRequestModel
import br.com.mobiplus.gitclient.domain.repository.PullRequestRepository

class GetPullRequestUseCase(
    private val pullRequestRepository: PullRequestRepository
) : BaseAsyncUseCase<ResultWrapper<PullRequestModel, BaseErrorData<ResultCode>>, GetPullRequestUseCase.Params>() {

    override suspend fun runAsync(params: Params): ResultWrapper<PullRequestModel, BaseErrorData<ResultCode>> {
        return pullRequestRepository.getPullRequest(
            owner = params.owner,
            gitRepoName = params.gitRepoName,
            pullRequestNumber = params.pullRequestNumber
        ).transformError { BaseErrorData(errorBody = ResultCode.DEFAULT_EXCEPTION) }
    }

    data class Params(
        val owner: String,
        val gitRepoName: String,
        val pullRequestNumber: Long
    )
}