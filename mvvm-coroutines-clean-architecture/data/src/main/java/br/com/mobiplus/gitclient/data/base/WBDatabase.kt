package br.com.mobiplus.gitclient.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mobiplus.gitclient.data.gitRepo.local.GitRepoDao
import br.com.mobiplus.gitclient.data.gitRepo.local.model.GitRepoEntityModel

@Database(version = 1, entities = [GitRepoEntityModel::class])
abstract class WBDatabase : RoomDatabase() {
    abstract fun gitRepoDao(): GitRepoDao
}