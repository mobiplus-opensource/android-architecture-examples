package br.com.mobiplus.gitclient.domain

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.model.GithubError

class FakeBaseErrorData {

    companion object {
        fun mockStringError() = BaseErrorData(
            errorBody = "# TEST MESSAGE"
        )

        fun mockStatusError() = BaseErrorData(
            errorBody = GithubError(
                message = "any",
                documentation_url = "http://test.url"
            )
        )

//        fun <ERROR_BODY> fakeError(
//            error: ERROR_BODY? = null,
//            errorMessage: String? = null
//        ): BaseErrorData<ERROR_BODY> {
//            return BaseErrorData(
//                errorBody = error,
//                errorMessage = errorMessage
//            )
//        }
    }

}