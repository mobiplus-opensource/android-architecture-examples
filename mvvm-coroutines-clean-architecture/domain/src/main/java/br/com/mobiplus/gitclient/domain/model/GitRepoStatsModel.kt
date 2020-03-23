package br.com.mobiplus.gitclient.domain.model

data class GitRepoStatsModel(
    val name: String,
    val closedIssues: Int,
    val openedIssues: Int,
    val mergedPullRequests: Int,
    val proposedPullRequests: Int
)