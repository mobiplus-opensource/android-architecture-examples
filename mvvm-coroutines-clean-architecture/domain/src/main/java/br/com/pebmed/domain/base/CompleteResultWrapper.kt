package br.com.pebmed.domain.base

class CompleteResultWrapper<SUCCESS, ERROR>(
    success: SUCCESS? = null,
    error: ERROR? = null,
    val keyValueMap: MutableMap<String, String>? = null,
    val resultCode: StatusType = StatusType.DEFAULT_EXCEPTION
) : ResultWrapper<SUCCESS, ERROR>(success, error) {

    override fun <TO_SUCCESS, TO_ERROR> transform(
        mapperSuccessFunction: (originalSuccess: SUCCESS) -> TO_SUCCESS,
        mapperErrorFunction: (originalError: ERROR?) -> TO_ERROR
    ): CompleteResultWrapper<TO_SUCCESS, TO_ERROR> {

        return if (this.success != null) {
            CompleteResultWrapper(
                success = mapperSuccessFunction.invoke(this.success),
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        } else {
            CompleteResultWrapper(
                error = mapperErrorFunction.invoke(this.error),
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        }
    }

    override fun <TO_SUCCESS> transformSuccess(
        mapperFunction: (originalSuccess: SUCCESS) -> TO_SUCCESS
    ): CompleteResultWrapper<TO_SUCCESS, ERROR> {
        return if (this.success != null) {
            CompleteResultWrapper(
                success = mapperFunction.invoke(this.success),
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        } else {
            CompleteResultWrapper(
                error = this.error,
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        }
    }

    override fun <TO_ERROR> transformError(
        mapperFunction: (originalError: ERROR?) -> TO_ERROR
    ): CompleteResultWrapper<SUCCESS, TO_ERROR> {
        return if (this.success != null) {
            CompleteResultWrapper(
                success = this.success,
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        } else {
            CompleteResultWrapper(
                error = mapperFunction.invoke(this.error),
                resultCode = this.resultCode,
                keyValueMap = this.keyValueMap
            )
        }
    }
}
