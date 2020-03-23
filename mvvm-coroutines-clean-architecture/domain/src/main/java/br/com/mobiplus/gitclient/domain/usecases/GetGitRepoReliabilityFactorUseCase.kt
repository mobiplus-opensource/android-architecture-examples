package br.com.mobiplus.gitclient.domain.usecases

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.base.usecase.BaseUseCase
import br.com.mobiplus.gitclient.domain.model.GitRepoStatsModel
import br.com.mobiplus.gitclient.domain.model.GithubError
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository

class GetGitRepoReliabilityFactorUseCase(
    private val gitRepoRepository: GitRepoRepository
) : BaseUseCase<ResultWrapper<Double?, BaseErrorData<GithubError>>, GetGitRepoReliabilityFactorUseCase.Params>() {

    override fun runSync(params: Params): ResultWrapper<Double?, BaseErrorData<GithubError>> {
        val (enabled, multiplier) = gitRepoRepository.getGitRepoReliabilityMultiplier()

        return if (enabled) {
            val result = gitRepoRepository.getGitRepoStats(params.owner, params.gitRepoName)

            result.transformSuccess(
                this.transformSuccess(multiplier!!)
            )
        } else {
            ResultWrapper(
                success = null
            )
        }
    }

    private fun transformSuccess(multiplier: Int): (GitRepoStatsModel) -> Double {
        return { gitRepoStatsModel ->
            calculateReliabilityFactor(
                engagementMultiplier = multiplier,
                closedIssues = gitRepoStatsModel.closedIssues,
                openedIssues = gitRepoStatsModel.openedIssues,
                mergedPullRequests = gitRepoStatsModel.mergedPullRequests,
                proposedPullRequests = gitRepoStatsModel.proposedPullRequests
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