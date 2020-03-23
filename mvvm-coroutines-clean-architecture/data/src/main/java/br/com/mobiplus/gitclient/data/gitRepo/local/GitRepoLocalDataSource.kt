package br.com.mobiplus.gitclient.data.gitRepo.local

import br.com.mobiplus.gitclient.data.base.BaseDataSourceImpl
import br.com.mobiplus.gitclient.data.gitRepo.local.model.GitRepoEntityModel
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper

class GitRepoLocalDataSource(private val gitRepoDao: GitRepoDao) : BaseDataSourceImpl() {
    suspend fun getGitRepoList(): ResultWrapper<List<GitRepoEntityModel>, BaseErrorData<Unit>> {
        return safeCall { gitRepoDao.getAll() }
    }

    suspend fun getGitRepo(id: Int): ResultWrapper<GitRepoEntityModel?, BaseErrorData<Unit>> {
        return safeCall { gitRepoDao.getFromId(id) }
    }

    suspend fun saveGitRepo(gitRepo: GitRepoEntityModel) {
        gitRepoDao.upsert(gitRepo)
    }

    suspend fun saveGitRepoList(gitRepoList: List<GitRepoEntityModel>) {
        gitRepoDao.upsert(gitRepoList)
    }
}