package br.com.mobiplus.gitclient.domain.usecases

import br.com.mobiplus.gitclient.domain.FakeGitRepoModel
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.repository.GitRepoRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetGitRepoListUseCaseTest {
    @MockK(relaxUnitFun = true)
    private lateinit var gitRepoRepository: GitRepoRepository

    @MockK(relaxUnitFun = true)
    private lateinit var getGitRepoReliabilityFactorUseCase: GetGitRepoReliabilityFactorUseCase

    private val fakePage = 1
    private val fakeLanguage = "java"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `SHOULD save last sync date WHEN force sync param is true`() = runBlocking {
        coEvery {
            gitRepoRepository.getGitRepoList(
                page = fakePage,
                language = fakeLanguage
            )
        } returns ResultWrapper(
            success = FakeGitRepoModel.mock(1)
        )

        GetGitRepoListUseCase(gitRepoRepository, getGitRepoReliabilityFactorUseCase).runAsync(GetGitRepoListUseCase.Params())

        coVerify {
            gitRepoRepository.getGitRepoList(
                page = fakePage,
                language = fakeLanguage
            )
        }

        confirmVerified(gitRepoRepository)
    }
}