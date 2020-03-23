package br.com.mobiplus.gitclient.domain

import br.com.mobiplus.gitclient.domain.model.GitRepoStatsModel

class FakeGitRepoStatsModel {
    companion object {
        fun mock() = GitRepoStatsModel(
            name = "",
            closedIssues = 1,
            openedIssues = 1,
            mergedPullRequests = 1,
            proposedPullRequests = 1
        )

        fun mock(
            closedIssues: Int,
            openedIssues: Int,
            mergedPullRequests: Int,
            proposedPullRequests: Int
        ) = GitRepoStatsModel(
            name = "",
            closedIssues = closedIssues,
            openedIssues = openedIssues,
            mergedPullRequests = mergedPullRequests,
            proposedPullRequests = proposedPullRequests
        )
    }
}