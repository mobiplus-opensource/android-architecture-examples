package br.com.pebmed.domain.usecases

import br.com.pebmed.domain.FakeGitRepoModel
import br.com.pebmed.domain.base.ResultWrapper
import br.com.pebmed.domain.repository.GitRepoRepository
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
                fromRemote = true,
                page = fakePage,
                language = fakeLanguage
            )
        } returns ResultWrapper(
            success = FakeGitRepoModel.mock(1)
        )

        GetGitRepoListUseCase(gitRepoRepository).runAsync(GetGitRepoListUseCase.Params(true))

        coVerify {
            gitRepoRepository.getGitRepoList(
                fromRemote = true,
                page = fakePage,
                language = fakeLanguage
            )
            gitRepoRepository.saveLastSyncDate(any())
        }

        confirmVerified(gitRepoRepository)
    }
}