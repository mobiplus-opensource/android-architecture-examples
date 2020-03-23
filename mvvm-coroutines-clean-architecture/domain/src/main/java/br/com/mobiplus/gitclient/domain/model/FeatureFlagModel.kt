package br.com.mobiplus.gitclient.domain.model

data class FeatureFlagModel<DATA>(
    val enabled: Boolean,
    val data: DATA? = null
)