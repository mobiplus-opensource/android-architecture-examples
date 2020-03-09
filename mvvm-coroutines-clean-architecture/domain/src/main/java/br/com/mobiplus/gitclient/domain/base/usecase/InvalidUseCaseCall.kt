package br.com.mobiplus.gitclient.domain.base.usecase

class InvalidUseCaseCall(customMessage: String): Exception("YOU SHALL NOT PASS!!!\nThis method could not be called. $customMessage")