package br.com.mobiplus.gitclient.data.di

import br.com.mobiplus.gitclient.data.base.SharedPreferencesUtil
import br.com.mobiplus.gitclient.data.pullRequest.PullRequestRepositoryImpl
import br.com.mobiplus.gitclient.data.gitrepo.GitRepoRepositoryImpl
import br.com.mobiplus.gitclient.data.gitrepo.local.GitRepoLocalDataSource
import br.com.mobiplus.gitclient.data.gitrepo.remote.GitRepoRemoteDataSource
import br.com.mobiplus.gitclient.domain.repository.PullRequestRepository
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<GitRepoRepository> {
        GitRepoRepositoryImpl(
            get() as GitRepoRemoteDataSource,
            get() as GitRepoLocalDataSource,
            get() as SharedPreferencesUtil
        )
    }

    factory<PullRequestRepository> {
        PullRequestRepositoryImpl(
            pullRequestRemoteDataSource = get()
        )
    }
}