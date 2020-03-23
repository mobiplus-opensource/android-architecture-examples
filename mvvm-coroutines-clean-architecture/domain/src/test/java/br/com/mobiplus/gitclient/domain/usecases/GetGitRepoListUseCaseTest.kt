package br.com.mobiplus.gitclient.domain.usecases

import br.com.mobiplus.gitclient.domain.FakeGitRepoModel
import br.com.mobiplus.gitclient.domain.FakeResultWrapper
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetGitRepoListUseCaseTest {
    @MockK(relaxUnitFun = true)
    private lateinit var gitRepoRepository: GitRepoRepository

    @MockK(relaxUnitFun = true)
    private lateinit var getGitRepoReliabilityFactorUseCase: GetGitRepoReliabilityFactorUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `SHOULD call correct functions WHEN runSync is called`() {
        coEvery {
            gitRepoRepository.getGitRepoList(any(), any())
        } returns FakeResultWrapper.mockSuccess(
            success = FakeGitRepoModel.mock(1)
        )

        every {
            getGitRepoReliabilityFactorUseCase.runSync(any())
        } returns FakeResultWrapper.mockSuccess(
            success = 2.0
        )

        runBlocking {
            GetGitRepoListUseCase(
                gitRepoRepository,
                getGitRepoReliabilityFactorUseCase
            ).runAsync(GetGitRepoListUseCase.Params())
        }

        coVerifySequence {
            gitRepoRepository.getGitRepoList(any(), any())
            getGitRepoReliabilityFactorUseCase.runSync(any())
        }
    }
}