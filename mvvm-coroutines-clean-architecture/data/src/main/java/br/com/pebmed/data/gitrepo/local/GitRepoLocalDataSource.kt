package br.com.pebmed.data.gitrepo.local

import br.com.pebmed.data.gitrepo.local.model.GitRepoEntityModel
import br.com.pebmed.data.base.BaseDataSourceImpl
import br.com.pebmed.domain.base.BaseErrorData
import br.com.pebmed.domain.base.ResultWrapper

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