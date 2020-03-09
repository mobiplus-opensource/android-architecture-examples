package br.com.mobiplus.gitclient.data.gitrepo.remote

import br.com.mobiplus.gitclient.data.gitrepo.remote.model.GetRepoListResponse
import br.com.mobiplus.gitclient.data.base.BaseDataSourceImpl
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.FullResultWrapper

class GitRepoRemoteDataSource(
    private val gitRepoApi: GitRepoApi
) : BaseDataSourceImpl() {
    suspend fun getGitRepoList(
        page: Int,
        language: String
    ): FullResultWrapper<GetRepoListResponse, BaseErrorData<Unit>> {
        return safeApiCall { gitRepoApi.getGitRepoList(page, language) }
    }
}