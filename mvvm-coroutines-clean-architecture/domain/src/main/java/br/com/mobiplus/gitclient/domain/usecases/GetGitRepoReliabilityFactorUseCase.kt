package br.com.mobiplus.gitclient.domain.usecases

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.base.usecase.BaseUseCase
import br.com.mobiplus.gitclient.domain.model.GitRepoStatsModel
import br.com.mobiplus.gitclient.domain.model.GithubError
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository

class GetGitRepoReliabilityFactorUseCase(
    private val gitRepoRepository: GitRepoRepository
) : BaseUseCase<ResultWrapper<Double, BaseErrorData<GithubError>>, GetGitRepoReliabilityFactorUseCase.Params>() {

    override fun runSync(params: Params): ResultWrapper<Double, BaseErrorData<GithubError>> {
        val result = gitRepoRepository.getGitRepoStats(params.owner, params.gitRepoName)

        return result.transformSuccess(
            transformSuccess()
        )
    }

    private fun transformSuccess(): (GitRepoStatsModel) -> Double {
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
        val issuesPoints = calculateIssuesPoints(
            engagementMultiplier = engagementMultiplier.toDouble(),
            closedIssues = closedIssues.toDouble(),
            openedIssues = openedIssues.toDouble()
        )

        val pullRequestPoints = calculatePullRequestsPoints(
            engagementMultiplier = engagementMultiplier.toDouble(),
            mergedPullRequests = mergedPullRequests.toDouble(),
            proposedPullRequests = proposedPullRequests.toDouble()
        )

        return (issuesPoints + pullRequestPoints) / 100
    }

    private fun calculateIssuesPoints(
        engagementMultiplier: Double,
        closedIssues: Double,
        openedIssues: Double
    ) = (closedIssues * engagementMultiplier) + openedIssues

    private fun calculatePullRequestsPoints(
        engagementMultiplier: Double,
        mergedPullRequests: Double,
        proposedPullRequests: Double
    ) = (mergedPullRequests * engagementMultiplier) + proposedPullRequests

    data class Params(
        val owner: String,
        val gitRepoName: String
    )
}