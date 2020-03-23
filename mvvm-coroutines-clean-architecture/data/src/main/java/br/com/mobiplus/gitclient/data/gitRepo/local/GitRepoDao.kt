package br.com.mobiplus.gitclient.data.gitRepo.local

import androidx.room.*
import br.com.mobiplus.gitclient.data.gitRepo.local.model.GitRepoEntityModel

@Dao
interface GitRepoDao {
    @Query("SELECT * FROM GitRepoModel")
    suspend fun getAll(): List<GitRepoEntityModel>

    @Query("SELECT * FROM GitRepoModel WHERE id = :id")
    suspend fun getFromId(id: Int): GitRepoEntityModel?

    @Query("DELETE FROM GitRepoModel")
    suspend fun deleteAll()

    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     * @return The SQLite row id
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(obj: GitRepoEntityModel): Long

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     * @return The SQLite row ids
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(obj: List<GitRepoEntityModel>): List<Long>

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    suspend fun update(obj: GitRepoEntityModel)

    /**
     * Update an array of objects from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    suspend fun update(obj: List<GitRepoEntityModel>)

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    suspend fun delete(obj: GitRepoEntityModel)

    @Transaction
    suspend fun upsert(obj: GitRepoEntityModel) {
        val id = insert(obj)
        if (id == -1L) {
            update(obj)
        }
    }

    @Transaction
    suspend fun upsert(objList: List<GitRepoEntityModel>) {
        val insertResult = insert(objList)
        val updateList = mutableListOf<GitRepoEntityModel>()

        for (i in insertResult.indices) {
            if (insertResult[i] == -1L) {
                updateList.add(objList[i])
            }
        }

        if (updateList.isNotEmpty()) {
            update(updateList)
        }
    }
}