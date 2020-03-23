package br.com.mobiplus.gitclient.data.di

import br.com.mobiplus.gitclient.data.gitRepo.GitRepoRepositoryImpl
import br.com.mobiplus.gitclient.data.gitRepo.remote.GitRepoAPIDataSource
import br.com.mobiplus.gitclient.data.gitRepo.remote.GitRepoStatsDataSource
import br.com.mobiplus.gitclient.data.pullRequest.PullRequestAPIDataSource
import br.com.mobiplus.gitclient.data.pullRequest.PullRequestRepositoryImpl
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository
import br.com.mobiplus.gitclient.domain.repository.PullRequestRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<GitRepoRepository> {
        GitRepoRepositoryImpl(
            get() as GitRepoAPIDataSource,
            get() as GitRepoStatsDataSource
        )
    }

    factory<PullRequestRepository> {
        PullRequestRepositoryImpl(
            get() as PullRequestAPIDataSource
        )
    }
}