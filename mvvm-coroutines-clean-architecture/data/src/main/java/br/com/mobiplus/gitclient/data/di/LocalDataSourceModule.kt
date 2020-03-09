package br.com.mobiplus.gitclient.data.di

import androidx.room.Room
import br.com.mobiplus.gitclient.data.base.WBDatabase
import br.com.mobiplus.gitclient.data.base.SharedPreferencesUtil
import br.com.mobiplus.gitclient.data.gitrepo.local.GitRepoLocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataSourceModule = module {
    single { SharedPreferencesUtil(androidContext()) }
    single { Room.databaseBuilder(androidContext(), WBDatabase::class.java, "basearch.db").build() }
    single { get<WBDatabase>().gitRepoDao() }

    factory { GitRepoLocalDataSource(gitRepoDao = get())}
}