package br.com.mobiplus.gitclient.data.fake

import br.com.mobiplus.gitclient.data.pullRequest.model.PullRequestResponseModel
import br.com.mobiplus.gitclient.data.pullRequest.model.UserResponseModel
import java.util.*

class FakePullRequestResponseModel {

    companion object {

        fun loadPullRequestResponse(
            userResponseModel: UserResponseModel
        ) = PullRequestResponseModel(
                number = 1,
                htmlUrl = "http://the.url",
                title = "Title",
                userModel = userResponseModel,
                body = "Body",
                createdAt = Date(),
                comments = 1,
                commits = 1,
                additions = 1,
                deletions = 1,
                changedFiles = 1
            )
    }
}