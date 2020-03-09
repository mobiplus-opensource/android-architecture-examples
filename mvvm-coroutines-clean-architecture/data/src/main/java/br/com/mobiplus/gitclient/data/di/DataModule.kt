package br.com.mobiplus.gitclient.data.di

val dataModule = listOf(
    networkModule,
    localDataSourceModule,
    remoteDataSourceModule,
    repositoryModule
)