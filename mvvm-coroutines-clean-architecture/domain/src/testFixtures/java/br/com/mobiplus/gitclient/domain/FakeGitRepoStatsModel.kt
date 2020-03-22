package br.com.mobiplus.gitclient.domain

import br.com.mobiplus.gitclient.domain.model.GitRepoStatsModel

class FakeGitRepoStatsModel {
    companion object {
        fun mock() = GitRepoStatsModel(
            name = "",
            closedIssuesOnLastMonth = 1,
            openedIssuesOnLastMonth = 1,
            mergedPullRequestsOnLastMonth = 1,
            proposedPullRequestsOnLastMonth = 1
        )

        fun mock(
            closedIssues: Int,
            openedIssues: Int,
            mergedPullRequests: Int,
            proposedPullRequests: Int
        ) = GitRepoStatsModel(
            name = "",
            closedIssuesOnLastMonth = closedIssues,
            openedIssuesOnLastMonth = openedIssues,
            mergedPullRequestsOnLastMonth = mergedPullRequests,
            proposedPullRequestsOnLastMonth = proposedPullRequests
        )
    }
}