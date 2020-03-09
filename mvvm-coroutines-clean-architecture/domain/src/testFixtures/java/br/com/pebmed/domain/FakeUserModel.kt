package br.com.pebmed.domain

import br.com.pebmed.domain.model.UserModel

class FakeUserModel {
    
    companion object {
        fun mock() = UserModel(
            login = "luis.fernandez",
            avatarUrl = "http://avatar.url"
        )
    }
}