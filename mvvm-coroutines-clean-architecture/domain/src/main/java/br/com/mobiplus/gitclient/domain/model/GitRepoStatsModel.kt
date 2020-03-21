package br.com.mobiplus.gitclient.domain.model

data class GitRepoStatsModel(
    val name: String,
    val closedIssuesOnLastMonth: Int,
    val openedIssuesOnLastMonth: Int,
    val mergedPullRequestsOnLastMonth: Int,
    val proposedPullRequestsOnLastMonth: Int
)