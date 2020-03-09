package br.com.pebmed.data.base

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.StatusType
import br.com.mobiplus.gitclient.domain.base.FullResultWrapper
import br.com.mobiplus.gitclient.domain.extensions.fromJsonGeneric
import com.google.gson.Gson
import retrofit2.Response

object ApiResponseHandler {
    inline fun <SUCCESS, reified ERROR> build(response: Response<SUCCESS>): FullResultWrapper<SUCCESS, BaseErrorData<ERROR>> {
        val headers = response.headers()

        val getHeadersHashMap = {
            val keyValueMap: MutableMap<String, String> = HashMap()

            headers.names().map { headerKey ->
                val headerValue = headers.get(headerKey)
                keyValueMap[headerKey] = headerValue ?: ""
            }

            keyValueMap
        }

        if (response.isSuccessful) {
            val body = response.body()
            return if (body != null)
                FullResultWrapper(
                    success = body,
                    keyValueMap = getHeadersHashMap(),
                    resultCode = StatusType.getByCode(response.code())
                )
            else
                FullResultWrapper(
                    keyValueMap = getHeadersHashMap(),
                    resultCode = StatusType.NULL_BODY_EXCEPTION
                )
        } else {
            var errorData: ERROR? = null

            if (ERROR::class != Unit::class) {
                val msg = response.errorBody()?.string()

                if (!msg.isNullOrEmpty()) {
                    errorData = Gson().fromJsonGeneric<ERROR>(msg)
                }
            }

            val remoteErrorData = BaseErrorData<ERROR>(
                errorData,
                response.message()
            )

            return FullResultWrapper(
                error = remoteErrorData,
                keyValueMap = getHeadersHashMap(),
                resultCode = StatusType.getByCode(response.code())
            )
        }
    }
}