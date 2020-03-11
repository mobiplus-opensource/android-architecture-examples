package br.com.mobiplus.gitclient.data.gitrepo

import br.com.mobiplus.gitclient.data.gitrepo.remote.GitRepoRemoteDataSource
import br.com.mobiplus.gitclient.data.gitrepo.remote.model.GetRepoListResponse
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

        return remoteResult.transform(
            this.handleGetGitRepoListSuccess(),
            this.handleGetGitRepoListError()
        )
    }

    private fun handleGetGitRepoListSuccess(): (GetRepoListResponse) -> List<GitRepoModel> {
        return { getReposResponse ->
            getReposResponse.gitRepoList.map { repoPayload ->
                repoPayload.mapTo()
            }
        }
    }

    private fun handleGetGitRepoListError(): (BaseErrorData<GithubError>) -> BaseErrorData<GithubError> {
        return { baseErrorData ->
            BaseErrorData(
                errorBody = baseErrorData.errorBody,
                errorMessage = "API ERROR: ${baseErrorData.errorMessage}"
            )
        }
    }

    override suspend fun getGitRepo(
        id: Int
    ): ResultWrapper<GitRepoModel, BaseErrorData<GithubError>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}