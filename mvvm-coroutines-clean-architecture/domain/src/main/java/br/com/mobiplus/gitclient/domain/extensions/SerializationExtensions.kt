package br.com.mobiplus.gitclient.domain.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJsonGeneric(json: String): T =
    this.fromJson(json, object : TypeToken<T>() {}.type)