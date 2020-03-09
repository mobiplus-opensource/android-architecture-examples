package br.com.pebmed.data.pullrequest.model.fake

import br.com.mobiplus.gitclient.data.pullRequest.model.UserResponseModel

class FakeUserResponseModel {
    companion object {
        fun loadUserResponse() = UserResponseModel(
            login = "luis.fernandez",
            avatarUrl = "http://avatar.url"
        )
    }
}