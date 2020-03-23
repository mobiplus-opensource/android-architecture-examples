package br.com.mobiplus.gitclient.data.gitrepo.remote

import br.com.mobiplus.gitclient.data.gitrepo.remote.model.GetRepoListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoApi {
    @GET("/search/repositories?sort=stars&per_page=10")
    suspend fun getGitRepoList(
        @Query("page") page: Int,
        @Query("q") language: String
    ): Response<GetRepoListResponse>
}