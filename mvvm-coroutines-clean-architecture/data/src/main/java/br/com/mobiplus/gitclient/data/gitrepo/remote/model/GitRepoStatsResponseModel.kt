package br.com.mobiplus.gitclient.data.gitrepo.remote.model

import br.com.mobiplus.gitclient.domain.model.GitRepoStatsModel

data class GitRepoStatsResponseModel(
    val name: String,
    val closedIssues: Int,
    val openedIssues: Int,
    val mergedPullRequests: Int,
    val proposedPullRequests: Int
) {
    fun mapTo() = GitRepoStatsModel(
        name = this.name,
        proposedPullRequests = this.proposedPullRequests,
        mergedPullRequests = this.mergedPullRequests,
        openedIssues = this.openedIssues,
        closedIssues = this.closedIssues
    )
}