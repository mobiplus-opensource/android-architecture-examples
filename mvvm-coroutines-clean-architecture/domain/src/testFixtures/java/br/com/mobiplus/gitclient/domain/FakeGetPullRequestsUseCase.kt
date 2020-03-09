package br.com.mobiplus.gitclient.domain

import br.com.mobiplus.gitclient.domain.usecases.GetPullRequestsUseCase

class FakeGetPullRequestsUseCase {

    class Params {

        companion object {
            fun mock() = GetPullRequestsUseCase.Params(
                owner = "OwnerModel",
                gitRepoName = "RepoName"
            )
        }
    }
}