package br.com.pebmed.domain

import br.com.pebmed.domain.model.OwnerModel

class FakeOwnerModel {
    
    companion object {
        fun mock() = OwnerModel(
            id = 1
        )
    }
}