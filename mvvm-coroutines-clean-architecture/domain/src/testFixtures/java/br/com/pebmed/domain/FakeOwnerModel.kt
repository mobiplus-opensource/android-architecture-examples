package br.com.pebmed.domain

import br.com.mobiplus.gitclient.domain.model.OwnerModel

class FakeOwnerModel {
    
    companion object {
        fun mock() = OwnerModel(
            id = 1
        )
    }
}