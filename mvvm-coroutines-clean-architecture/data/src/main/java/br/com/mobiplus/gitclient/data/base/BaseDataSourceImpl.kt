package br.com.mobiplus.gitclient.data.base

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.ResultCode
import br.com.mobiplus.gitclient.domain.base.resultwrapper.FullResultWrapper
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseDataSourceImpl {
    inline fun <SUCCESS, reified ERROR> safeApiCall(executeApiAsync: () -> Response<SUCCESS>): FullResultWrapper<SUCCESS, BaseErrorData<ERROR>> {
        return try {
            val response = executeApiAsync.invoke()

            ApiResponseHandler.build(response)
        } catch (exception: Exception) {
            val baseErrorData = BaseErrorData<ERROR>(
                errorMessage = exception.message
            )

            val statusCode = when (exception) {
                is SocketTimeoutException -> {
                    ResultCode.SOCKET_TIMEOUT_EXCEPTION
                }
                is UnknownHostException -> {
                    ResultCode.UNKNOWN_HOST_EXCEPTION
                }
                is ConnectException -> {
                    ResultCode.CONNECT_EXCEPTION
                }
                is NoRouteToHostException -> {
                    ResultCode.NO_ROUTE_TO_HOST_EXCEPTION
                }
                is IOException -> {
                    ResultCode.IO_EXCEPTION
                }
                else -> {
                    ResultCode.DEFAULT_EXCEPTION
                }
            }

            FullResultWrapper(
                error = baseErrorData,
                resultCode = statusCode
            )
        }
    }

    inline fun <SUCCESS, reified ERROR> safeCall(executeAsync: () -> SUCCESS): ResultWrapper<SUCCESS, BaseErrorData<ERROR>> {
        return try {
            val response = executeAsync.invoke()
            FullResultWrapper(
                success = response
            )
        } catch (exception: Exception) {
            val baseErrorData =
                BaseErrorData<ERROR>(errorMessage = exception.message)
            FullResultWrapper(
                error = baseErrorData
            )
        }
    }
}
