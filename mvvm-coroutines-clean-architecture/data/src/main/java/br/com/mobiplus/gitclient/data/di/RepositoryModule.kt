package br.com.mobiplus.gitclient.data.di

import br.com.mobiplus.gitclient.data.pullRequest.PullRequestRepositoryImpl
import br.com.mobiplus.gitclient.data.gitrepo.GitRepoRepositoryImpl
import br.com.mobiplus.gitclient.data.gitrepo.remote.GitRepoAPIDataSource
import br.com.mobiplus.gitclient.data.pullRequest.PullRequestAPIDataSource
import br.com.mobiplus.gitclient.domain.repository.PullRequestRepository
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<GitRepoRepository> {
        GitRepoRepositoryImpl(
            get() as GitRepoAPIDataSource
        )
    }

    factory<PullRequestRepository> {
        PullRequestRepositoryImpl(
            get() as PullRequestAPIDataSource
        )
    }
}