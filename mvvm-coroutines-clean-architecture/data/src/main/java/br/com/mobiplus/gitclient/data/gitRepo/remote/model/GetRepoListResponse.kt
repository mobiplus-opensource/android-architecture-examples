package br.com.mobiplus.gitclient.data.gitRepo.remote.model

import com.google.gson.annotations.SerializedName

data class GetRepoListResponse(
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val gitRepoList: List<GitRepoResponse>,
    @SerializedName("total_count") val totalCount: Int
)