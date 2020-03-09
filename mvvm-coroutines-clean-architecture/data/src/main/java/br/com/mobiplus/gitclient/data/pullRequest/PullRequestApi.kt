package br.com.mobiplus.gitclient.data.pullRequest

import br.com.mobiplus.gitclient.data.pullRequest.model.PullRequestResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestApi {
    @GET("/repos/{ownerModel}/{gitRepoName}/pulls?state=all")
    suspend fun getPullRequests(
        @Path("ownerModel") owner: String,
        @Path("gitRepoName") gitRepoName: String
    ): Response<List<PullRequestResponseModel>>

    @GET("/repos/{ownerModel}/{gitRepoName}/pulls/{pullRequestNumber}")
    suspend fun getPullRequest(
        @Path("ownerModel") owner: String,
        @Path("gitRepoName") gitRepoName: String,
        @Path("pullRequestNumber") pullRequestNumber: Long
    ): Response<PullRequestResponseModel>
}