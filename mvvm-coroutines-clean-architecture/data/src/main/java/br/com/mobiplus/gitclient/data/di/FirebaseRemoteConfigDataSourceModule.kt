package br.com.mobiplus.gitclient.data.di

import br.com.mobiplus.gitclient.data.featureFlag.FeatureFlagDataSource
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments")
val firebaseRemoteConfigDataSourceModule = module {

    factory<FeatureFlagDataSource>{
        FeatureFlagDataSource()
    }
}