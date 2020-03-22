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
) : BaseUseCase<ResultWrapper<Double, BaseErrorData<GithubError>>, GetGitRepoReliabilityFactorUseCase.Params>() {

    override fun runSync(params: Params): ResultWrapper<Double, BaseErrorData<GithubError>> {
        val result = gitRepoRepository.getGitRepoStats(params.owner, params.gitRepoName)

        return result.transformSuccess(
            handleSuccess()
        )
    }

    private fun handleSuccess(): (GitRepoStatsModel) -> Double {
        return { gitRepoStatsModel ->
            calculateReliabilityFactor(
                engagementMultiplier = 4,
                closedIssues = gitRepoStatsModel.closedIssuesOnLastMonth,
                openedIssues = gitRepoStatsModel.openedIssuesOnLastMonth,
                mergedPullRequests = gitRepoStatsModel.mergedPullRequestsOnLastMonth,
                proposedPullRequests = gitRepoStatsModel.proposedPullRequestsOnLastMonth
            )
        }
    }

    private fun calculateReliabilityFactor(
        engagementMultiplier: Int,
        closedIssues: Int,
        openedIssues: Int,
        mergedPullRequests: Int,
        proposedPullRequests: Int
    ): Double {
        return ((openedIssues.toDouble() +
                proposedPullRequests.toDouble() +
                (closedIssues * engagementMultiplier).toDouble() +
                (mergedPullRequests * engagementMultiplier).toDouble()
                ) / 100)
    }

    data class Params(
        val owner: String,
        val gitRepoName: String
    )
}