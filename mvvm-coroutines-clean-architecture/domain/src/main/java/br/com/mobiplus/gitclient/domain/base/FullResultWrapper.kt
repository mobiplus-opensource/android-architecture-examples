package br.com.mobiplus.gitclient.domain.base

class FullResultWrapper<SUCCESS, ERROR>(
    success: SUCCESS? = null,
    error: ERROR? = null,
    val keyValueMap: MutableMap<String, String>? = null,
    val resultCode: ResultCode = ResultCode.DEFAULT_EXCEPTION
) : ResultWrapper<SUCCESS, ERROR>(success, error) {

    override fun <TO_SUCCESS, TO_ERROR> transform(
        mapperSuccessFunction: (originalSuccess: SUCCESS) -> TO_SUCCESS,
        mapperErrorFunction: (originalError: ERROR?) -> TO_ERROR
    ): FullResultWrapper<TO_SUCCESS, TO_ERROR> {

        return if (this.success != null) {
            FullResultWrapper(
                success = mapperSuccessFunction.invoke(this.success),
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        } else {
            FullResultWrapper(
                error = mapperErrorFunction.invoke(this.error),
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        }
    }

    override fun <TO_SUCCESS> transformSuccess(
        mapperFunction: (originalSuccess: SUCCESS) -> TO_SUCCESS
    ): FullResultWrapper<TO_SUCCESS, ERROR> {
        return if (this.success != null) {
            FullResultWrapper(
                success = mapperFunction.invoke(this.success),
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        } else {
            FullResultWrapper(
                error = this.error,
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        }
    }

    override fun <TO_ERROR> transformError(
        mapperFunction: (originalError: ERROR?) -> TO_ERROR
    ): FullResultWrapper<SUCCESS, TO_ERROR> {
        return if (this.success != null) {
            FullResultWrapper(
                success = this.success,
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        } else {
            FullResultWrapper(
                error = mapperFunction.invoke(this.error),
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        }
    }
}
