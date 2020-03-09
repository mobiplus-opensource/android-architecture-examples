package br.com.mobiplus.gitclient.data.gitrepo

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.model.GitRepoModel
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository
import br.com.mobiplus.gitclient.data.base.SharedPreferencesUtil
import br.com.mobiplus.gitclient.data.gitrepo.local.GitRepoLocalDataSource
import br.com.mobiplus.gitclient.data.gitrepo.remote.GitRepoRemoteDataSource

class GitRepoRepositoryImpl(
    private val gitRepoRemoteDataSource: GitRepoRemoteDataSource,
    private val gitRepoLocalDataSource: GitRepoLocalDataSource,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : GitRepoRepository {
    override fun getLastSyncDate(): String {
        return sharedPreferencesUtil.lastRepoSyncDate
    }

    override fun saveLastSyncDate(dateString: String) {
        sharedPreferencesUtil.lastRepoSyncDate = dateString
    }

    override suspend fun getRemoteGitRepoList(
        page: Int,
        language: String
    ): ResultWrapper<List<GitRepoModel>, BaseErrorData<Unit>> {
        val remoteResult = gitRepoRemoteDataSource.getGitRepoList(page, language)

        return remoteResult.transformSuccess { getReposResponse ->
            getReposResponse.gitRepoList.map { repoPayload ->
                repoPayload.mapTo()
            }
        }
    }

    override suspend fun getLocalGitRepoList(): ResultWrapper<List<GitRepoModel>, BaseErrorData<Unit>> {
        val localResponse = gitRepoLocalDataSource.getGitRepoList()

        return localResponse.transformSuccess { repoEntities ->
            repoEntities.map { repoEntity ->
                repoEntity.mapTo()
            }
        }
    }

    override suspend fun getGitRepoList(
        fromRemote: Boolean,
        page: Int,
        language: String
    ): ResultWrapper<List<GitRepoModel>, BaseErrorData<Unit>> {
        return if (fromRemote) {
            getRemoteGitRepoList(page, language)
        } else {
            getLocalGitRepoList()
        }
    }

    override suspend fun getGitRepo(
        id: Int,
        fromRemote: Boolean
    ): ResultWrapper<GitRepoModel, BaseErrorData<Unit>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}