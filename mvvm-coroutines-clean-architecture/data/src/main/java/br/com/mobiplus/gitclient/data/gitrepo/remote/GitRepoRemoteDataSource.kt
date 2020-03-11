package br.com.mobiplus.gitclient.data.gitrepo.remote

import br.com.mobiplus.gitclient.data.gitrepo.remote.model.GetRepoListResponse
import br.com.mobiplus.gitclient.data.base.BaseDataSourceImpl
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.FullResultWrapper
import br.com.mobiplus.gitclient.domain.model.GithubError

class GitRepoRemoteDataSource(
    private val gitRepoApi: GitRepoApi
) : BaseDataSourceImpl() {
    suspend fun getGitRepoList(
        page: Int,
        language: String
    ): FullResultWrapper<GetRepoListResponse, BaseErrorData<GithubError>> {
        return safeApiCall { gitRepoApi.getGitRepoList(page, language) }
    }
}