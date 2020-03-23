package br.com.mobiplus.gitclient.domain

import br.com.mobiplus.gitclient.domain.usecases.GetGitRepoListUseCase

class FakeGetGitRepoListUseCase {

    class Params {

        companion object {
            fun mock() = GetGitRepoListUseCase.Params()
        }
    }
}