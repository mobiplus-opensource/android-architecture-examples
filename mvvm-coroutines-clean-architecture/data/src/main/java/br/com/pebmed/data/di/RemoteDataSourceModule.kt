package br.com.pebmed.data.di

import br.com.pebmed.data.gitrepo.remote.GitRepoApi
import br.com.pebmed.data.pullRequest.PullRequestRemoteDataSource
import br.com.pebmed.data.gitrepo.remote.GitRepoRemoteDataSource
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