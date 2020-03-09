package br.com.mobiplus.gitclient.presentation

import br.com.mobiplus.gitclient.data.di.dataModule
import br.com.mobiplus.gitclient.domain.di.domainModule
import br.com.mobiplus.gitclient.presentation.di.presentationModule

val mainModule = dataModule + domainModule + presentationModule