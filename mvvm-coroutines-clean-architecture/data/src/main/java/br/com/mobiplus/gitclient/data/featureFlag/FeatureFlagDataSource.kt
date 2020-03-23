package br.com.mobiplus.gitclient.data.featureFlag

import br.com.mobiplus.gitclient.domain.model.FeatureFlagModel

class FeatureFlagDataSource {

    fun getReliabilityFactorMultiplier() = FeatureFlagModel(
        enabled = true,
        data = 4
    )
}