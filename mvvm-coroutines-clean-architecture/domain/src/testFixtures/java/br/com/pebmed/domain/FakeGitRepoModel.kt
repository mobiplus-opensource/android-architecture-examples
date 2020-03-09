package br.com.pebmed.domain

import br.com.pebmed.domain.model.GitRepoModel

class FakeGitRepoModel {
    
    companion object {
        fun mock(itemsOnList: Int): List<GitRepoModel> {
            val list = mutableListOf<GitRepoModel>()

            for (index in 0 .. itemsOnList) {
                list.add(
                    mock()
                )
            }

            return list
        }

        fun mock() = GitRepoModel(
            id = 1,
            ownerModel = FakeOwnerModel.mock()
        )
    }
}