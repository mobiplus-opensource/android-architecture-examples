package br.com.pebmed.domain.usecases

import br.com.pebmed.domain.base.BaseErrorData
import br.com.pebmed.domain.base.BaseErrorStatus
import br.com.pebmed.domain.base.ResultWrapper
import br.com.pebmed.domain.base.usecase.BaseAsyncUseCase
import br.com.pebmed.domain.model.GitRepoModel
import br.com.pebmed.domain.extensions.getCurrentDateTime
import br.com.pebmed.domain.extensions.toCacheFormat
import br.com.pebmed.domain.repository.GitRepoRepository
import br.com.pebmed.domain.usecases.GetGitRepoListUseCase.Params

/**
 * @Regra de negócio:
 * 1) Buscar todos os repositórios de acordo com os parâmetros enviados
 * 2) É necessário persistir a ultima data de sincronização caso o resultado seja sucesso e o
 * parâmetro de sincronização esteja true
 */
class GetGitRepoListUseCase(
    private val gitRepoRepository: GitRepoRepository
) : BaseAsyncUseCase<ResultWrapper<List<GitRepoModel>, BaseErrorData<BaseErrorStatus>>, Params>() {

    override suspend fun runAsync(params: Params): ResultWrapper<List<GitRepoModel>, BaseErrorData<BaseErrorStatus>> {
        val result = gitRepoRepository.getGitRepoList(
            fromRemote = params.forceSync,
            page = 1,
            language = "java"
        )

        if (result.success != null && params.forceSync) {
            gitRepoRepository.saveLastSyncDate(getCurrentDateTime().toCacheFormat())
        }

        return result.transformError { BaseErrorData(errorBody = BaseErrorStatus.DEFAULT_ERROR) }
    }

    data class Params(val forceSync: Boolean)
}