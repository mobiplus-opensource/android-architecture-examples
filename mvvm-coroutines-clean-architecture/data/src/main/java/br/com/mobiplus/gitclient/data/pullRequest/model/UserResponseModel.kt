package br.com.mobiplus.gitclient.data.pullRequest.model

import br.com.mobiplus.gitclient.domain.model.UserModel
import com.google.gson.annotations.SerializedName

data class UserResponseModel(
        @SerializedName("login") val login: String,
        @SerializedName("avatar_url") val avatarUrl: String
) {
    fun mapTo() = UserModel (
        login = this.login,
        avatarUrl = this.avatarUrl
    )
}