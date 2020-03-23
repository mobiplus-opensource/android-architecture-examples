package br.com.mobiplus.gitclient.data.gitrepo.remote.model

import br.com.mobiplus.gitclient.domain.model.GitRepoStatsModel

data class GitRepoStatsResponseModel(
    val name: String,
    val closedIssuesOnLastMonth: Int,
    val openedIssuesOnLastMonth: Int,
    val mergedPullRequestsOnLastMonth: Int,
    val proposedPullRequestsOnLastMonth: Int
) {
    fun mapTo() = GitRepoStatsModel(
        name = this.name,
        proposedPullRequestsOnLastMonth = this.proposedPullRequestsOnLastMonth,
        mergedPullRequestsOnLastMonth = this.mergedPullRequestsOnLastMonth,
        openedIssuesOnLastMonth = this.openedIssuesOnLastMonth,
        closedIssuesOnLastMonth = this.closedIssuesOnLastMonth
    )
}