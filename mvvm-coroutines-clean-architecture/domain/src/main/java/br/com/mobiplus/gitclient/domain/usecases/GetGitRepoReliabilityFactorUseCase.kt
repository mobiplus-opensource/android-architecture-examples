package br.com.mobiplus.gitclient.domain.usecases

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.base.usecase.BaseUseCase
import br.com.mobiplus.gitclient.domain.model.GitRepoStatsModel
import br.com.mobiplus.gitclient.domain.model.GithubError
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository
import kotlin.random.Random

class GetGitRepoReliabilityFactorUseCase(
    private val gitRepoRepository: GitRepoRepository
) : BaseUseCase<ResultWrapper<Int, BaseErrorData<GithubError>>, GetGitRepoReliabilityFactorUseCase.Params>() {

    override fun runSync(params: Params): ResultWrapper<Int, BaseErrorData<GithubError>> {
        val result = gitRepoRepository.getGitRepoStats(params.owner, params.gitRepoName)

        return result.transformSuccess(
            handleSuccess()
        )
    }

    private fun handleSuccess(): (GitRepoStatsModel) -> Int {
        return { gitRepoStatsModel ->
            calculateReliabilityFactor(
                Random.nextInt(2, 4),
                gitRepoStatsModel.closedIssuesOnLastMonth,
                gitRepoStatsModel.openedIssuesOnLastMonth,
                gitRepoStatsModel.mergedPullRequestsOnLastMonth,
                gitRepoStatsModel.proposedPullRequestsOnLastMonth
            )
        }
    }

    private fun calculateReliabilityFactor(
        engagementMultiplier: Int,
        closedIssues: Int,
        openedIssues: Int,
        mergedPullRequests: Int,
        proposedPullRequests: Int
    ): Int {
        return (openedIssues +
                proposedPullRequests +
                (closedIssues * engagementMultiplier) +
                (mergedPullRequests * engagementMultiplier)
                ) / 100
    }

    data class Params(
        val owner: String,
        val gitRepoName: String
    )
}