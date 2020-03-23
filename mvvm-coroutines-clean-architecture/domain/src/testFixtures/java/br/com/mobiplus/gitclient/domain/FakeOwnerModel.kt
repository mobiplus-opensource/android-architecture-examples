package br.com.mobiplus.gitclient.domain

import br.com.mobiplus.gitclient.domain.model.OwnerModel

class FakeOwnerModel {

    companion object {
        fun mock() = OwnerModel(
            id = 1,
            login = "#FAKE_LOGIN"
        )
    }
}