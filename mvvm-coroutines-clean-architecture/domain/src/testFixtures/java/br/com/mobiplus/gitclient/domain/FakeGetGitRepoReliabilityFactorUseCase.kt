package br.com.mobiplus.gitclient.domain

import br.com.mobiplus.gitclient.domain.usecases.GetGitRepoReliabilityFactorUseCase

class FakeGetGitRepoReliabilityFactorUseCase {

    class Params {

        companion object {
            fun mock() = GetGitRepoReliabilityFactorUseCase.Params(
                owner = "OwnerModel",
                gitRepoName = "RepoName"
            )
        }
    }
}