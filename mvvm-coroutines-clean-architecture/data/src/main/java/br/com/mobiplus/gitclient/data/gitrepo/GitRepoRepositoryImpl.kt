package br.com.mobiplus.gitclient.data.gitrepo

import br.com.mobiplus.gitclient.data.gitrepo.remote.GitRepoRemoteDataSource
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.model.GitRepoModel
import br.com.mobiplus.gitclient.domain.model.GithubError
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository

class GitRepoRepositoryImpl(
    private val gitRepoRemoteDataSource: GitRepoRemoteDataSource
) : GitRepoRepository {

    override suspend fun getGitRepoList(
        page: Int,
        language: String
    ): ResultWrapper<List<GitRepoModel>, BaseErrorData<GithubError>> {
        val remoteResult = gitRepoRemoteDataSource.getGitRepoList(page, language)

        return remoteResult.transformSuccess { getReposResponse ->
            getReposResponse.gitRepoList.map { repoPayload ->
                repoPayload.mapTo()
            }
        }
    }

    override suspend fun getGitRepo(
        id: Int
    ): ResultWrapper<GitRepoModel, BaseErrorData<GithubError>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}