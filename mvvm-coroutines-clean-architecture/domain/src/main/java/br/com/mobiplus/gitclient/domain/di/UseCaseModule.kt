package br.com.mobiplus.gitclient.domain.di

import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository
import br.com.mobiplus.gitclient.domain.usecases.GetPullRequestUseCase
import br.com.mobiplus.gitclient.domain.usecases.GetPullRequestsUseCase
import br.com.mobiplus.gitclient.domain.usecases.GetGitRepoListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        GetGitRepoListUseCase(
            get() as GitRepoRepository
        )
    }

    factory {
        GetPullRequestsUseCase(
            pullRequestRepository = get()
        )
    }

    factory {
        GetPullRequestUseCase(
            pullRequestRepository = get()
        )
    }
}