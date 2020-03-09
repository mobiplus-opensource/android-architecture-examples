package br.com.pebmed.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.pebmed.data.gitrepo.local.model.GitRepoEntityModel
import br.com.pebmed.data.gitrepo.local.GitRepoDao

@Database(version = 1, entities = [GitRepoEntityModel::class])
abstract class WBDatabase : RoomDatabase() {
    abstract fun gitRepoDao(): GitRepoDao
}