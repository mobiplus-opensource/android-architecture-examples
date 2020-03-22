package br.com.mobiplus.gitclient.presentation.ui.main.model

import br.com.mobiplus.gitclient.domain.model.GitRepoModel

data class GitRepoUIModel(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var stargazersCount: Int? = null,
    var forksCount: Int? = null,
    var reliabilityFactor: Double? = null,
    var language: String? = null,
    var openIssuesCount: Int? = null,
    var ownerLogin: String? = null,
    var ownerAvatarUrl: String? = null
) {
    fun mapFrom(from: GitRepoModel) {
        this.id = from.id
        this.name = from.name
        this.description = from.description
        this.stargazersCount = from.stargazersCount
        this.forksCount = from.forksCount
        this.reliabilityFactor = from.reliabilityFactor
        this.language = from.language
        this.openIssuesCount = from.openIssuesCount
        this.ownerLogin = from.ownerModel.login
        this.ownerAvatarUrl = from.ownerModel.avatarUrl
    }
}
