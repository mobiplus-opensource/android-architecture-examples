package br.com.mobiplus.gitclient.domain.usecases

import br.com.mobiplus.gitclient.domain.FakeGetGitRepoReliabilityFactorUseCase
import br.com.mobiplus.gitclient.domain.FakeGitRepoStatsModel
import br.com.mobiplus.gitclient.domain.FakeResultWrapper
import br.com.mobiplus.gitclient.domain.model.FeatureFlagModel
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetGitRepoReliabilityFactorUseCaseTest {

    @MockK(relaxUnitFun = true)
    private lateinit var gitRepoRepository: GitRepoRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `SHOULD call correct functions WHEN runSync is called`() {
        every {
            gitRepoRepository.getGitRepoStats(any(), any())
        } returns FakeResultWrapper.mockFullSuccess(
            success = FakeGitRepoStatsModel.mock()
        )

        every {
            gitRepoRepository.getGitRepoReliabilityMultiplier()
        } returns FeatureFlagModel(
            enabled = true,
            data = 4
        )

        val params = FakeGetGitRepoReliabilityFactorUseCase.Params.mock()

        GetGitRepoReliabilityFactorUseCase(
            gitRepoRepository = gitRepoRepository
        ).runSync(params)

        coVerifySequence {
            gitRepoRepository.getGitRepoReliabilityMultiplier()
            gitRepoRepository.getGitRepoStats(any(), any())
        }
    }

    @Test
    fun `SHOULD calculate correct result WHEN result is success`() {
        every {
            gitRepoRepository.getGitRepoStats(any(), any())
        } returns FakeResultWrapper.mockFullSuccess(
            success = FakeGitRepoStatsModel.mock(
                closedIssues = 5,
                openedIssues = 10,
                mergedPullRequests = 5,
                proposedPullRequests = 10
            )
        )

        every {
            gitRepoRepository.getGitRepoReliabilityMultiplier()
        } returns FeatureFlagModel(
            enabled = true,
            data = 4
        )

        val params = FakeGetGitRepoReliabilityFactorUseCase.Params.mock()

        val result = GetGitRepoReliabilityFactorUseCase(
            gitRepoRepository = gitRepoRepository
        ).runSync(params)

        assertEquals(0.6, result.success)
    }
}