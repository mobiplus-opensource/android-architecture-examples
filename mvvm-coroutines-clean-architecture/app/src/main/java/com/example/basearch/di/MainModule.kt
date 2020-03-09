package com.example.basearch.di

import br.com.pebmed.data.di.dataModule
import br.com.mobiplus.gitclient.domain.di.domainModule
import com.example.basearch.presentation.di.presentationModule

val mainModule = dataModule + domainModule + presentationModule