package br.com.mobiplus.gitclient.data.repository

import br.com.mobiplus.gitclient.data.pullRequest.PullRequestRemoteDataSource
import br.com.mobiplus.gitclient.data.pullRequest.PullRequestRepositoryImpl
import br.com.mobiplus.gitclient.data.pullRequest.model.PullRequestResponseModel
import br.com.mobiplus.gitclient.data.pullRequest.model.UserResponseModel
import br.com.mobiplus.gitclient.data.fake.FakePullRequestResponseModel
import br.com.mobiplus.gitclient.data.fake.FakeUserResponseModel
import br.com.mobiplus.gitclient.domain.base.FullResultWrapper
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class PullRequestRepositoryImplTest {

    @MockK
    private lateinit var pullRequestRemoteDataSource: PullRequestRemoteDataSource

    private lateinit var userModel: UserResponseModel
    private lateinit var pullRequestModel: PullRequestResponseModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        userModel = FakeUserResponseModel.loadUserResponse()
        pullRequestModel = FakePullRequestResponseModel.loadPullRequestResponse(userModel)
    }

    @Test
    fun listPullRequests() {

        coEvery {
            pullRequestRemoteDataSource.getPullRequests(any(), any())
        } returns FullResultWrapper(
            success = listOf(pullRequestModel)
        )

        runBlocking {
            PullRequestRepositoryImpl(pullRequestRemoteDataSource).getPullRequests(
                owner = "",
                gitRepoName = ""
            )
        }


        coVerify {
            pullRequestRemoteDataSource.getPullRequests(any(), any())
        }

        confirmVerified(pullRequestRemoteDataSource)
    }

    @Test
    fun `SHOULD call functions in the correct order`() {

        coEvery {
            pullRequestRemoteDataSource.getPullRequests(any(), any())
        } returns FullResultWrapper(
            success = listOf(pullRequestModel)
        )

        val pullRequestRepositoryImpl =
            spyk(
                PullRequestRepositoryImpl(
                    pullRequestRemoteDataSource
                ), recordPrivateCalls = true)
        runBlocking {
            pullRequestRepositoryImpl.getPullRequests("", "")
        }

        coVerify {
            pullRequestRepositoryImpl.getPullRequests(any(), any())
            pullRequestRepositoryImpl["handleGetPullRequestsSuccess"]()
            //pullRequestRepositoryImpl["handleListPullRequestsError"]()
        }

        coVerifySequence {
            pullRequestRepositoryImpl.getPullRequests(any(), any())
            pullRequestRepositoryImpl["handleGetPullRequestsSuccess"]()
            //pullRequestRepositoryImpl["handleListPullRequestsError"]()
        }

        confirmVerified(
            pullRequestRepositoryImpl
        )
    }

    @After
    fun tearDown() {
    }
}