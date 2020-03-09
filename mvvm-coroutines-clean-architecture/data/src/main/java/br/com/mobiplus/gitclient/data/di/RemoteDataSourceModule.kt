package br.com.mobiplus.gitclient.data.di

import br.com.mobiplus.gitclient.data.gitrepo.remote.GitRepoApi
import br.com.mobiplus.gitclient.data.pullRequest.PullRequestRemoteDataSource
import br.com.mobiplus.gitclient.data.gitrepo.remote.GitRepoRemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory {
        GitRepoRemoteDataSource(
            get() as GitRepoApi
        )
    }

    factory {
        PullRequestRemoteDataSource(
            pullRequestApi = get()
        )
    }
}