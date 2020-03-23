package br.com.mobiplus.gitclient.data.di

import br.com.mobiplus.gitclient.data.gitRepo.remote.GitRepoAPIDataSource
import br.com.mobiplus.gitclient.data.gitRepo.remote.GitRepoApi
import br.com.mobiplus.gitclient.data.gitRepo.remote.GitRepoStatsDataSource
import br.com.mobiplus.gitclient.data.pullRequest.PullRequestAPIDataSource
import br.com.mobiplus.gitclient.data.pullRequest.PullRequestApi
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments")
val apiDataSourceModule = module {
    factory {
        GitRepoAPIDataSource(
            get() as GitRepoApi
        )
    }

    factory {
        GitRepoStatsDataSource(
            get() as GitRepoApi
        )
    }

    factory {
        PullRequestAPIDataSource(
            get() as PullRequestApi
        )
    }
}