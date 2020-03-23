package br.com.mobiplus.gitclient.domain.di

import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository
import br.com.mobiplus.gitclient.domain.usecases.GetGitRepoListUseCase
import br.com.mobiplus.gitclient.domain.usecases.GetGitRepoReliabilityFactorUseCase
import br.com.mobiplus.gitclient.domain.usecases.GetPullRequestUseCase
import br.com.mobiplus.gitclient.domain.usecases.GetPullRequestsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        GetGitRepoListUseCase(
            get() as GitRepoRepository,
            get() as GetGitRepoReliabilityFactorUseCase
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

    factory {
        GetGitRepoReliabilityFactorUseCase(
            get() as GitRepoRepository
        )
    }
}