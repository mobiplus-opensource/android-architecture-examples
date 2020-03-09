package br.com.mobiplus.gitclient.domain

import br.com.mobiplus.gitclient.domain.model.UserModel

class FakeUserModel {
    
    companion object {
        fun mock() = UserModel(
            login = "luis.fernandez",
            avatarUrl = "http://avatar.url"
        )
    }
}