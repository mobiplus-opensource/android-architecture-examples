package br.com.pebmed.domain.repository

import br.com.pebmed.domain.base.BaseErrorData
import br.com.pebmed.domain.base.ResultWrapper
import br.com.pebmed.domain.model.GitRepoModel

interface GitRepoRepository {
    fun getLastSyncDate(): String

    fun saveLastSyncDate(dateString: String)

    suspend fun getRemoteGitRepoList(
        page: Int,
        language: String
    ): ResultWrapper<List<GitRepoModel>, BaseErrorData<Unit>>

    suspend fun getLocalGitRepoList(): ResultWrapper<List<GitRepoModel>, BaseErrorData<Unit>>

    suspend fun getGitRepoList(
        fromRemote: Boolean = false,
        page: Int,
        language: String
    ): ResultWrapper<List<GitRepoModel>, BaseErrorData<Unit>>

    suspend fun getGitRepo(
        id: Int,
        fromRemote: Boolean = false
    ): ResultWrapper<GitRepoModel, BaseErrorData<Unit>>
}