package br.com.pebmed.domain.di

import br.com.pebmed.domain.repository.GitRepoRepository
import br.com.pebmed.domain.usecases.GetPullRequestUseCase
import br.com.pebmed.domain.usecases.GetPullRequestsUseCase
import br.com.pebmed.domain.usecases.GetGitRepoListUseCase
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