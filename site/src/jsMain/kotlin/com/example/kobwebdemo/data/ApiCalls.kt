package com.example.kobwebdemo.data

import com.example.kobwebdemo.model.ApiListResponse
import com.example.kobwebdemo.model.Subscriber
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend fun addSubscriber(subscriber: Subscriber): String {
    return window.api.tryPost(
        apiPath = "addsubscriber",
        body = Json.encodeToString(subscriber).encodeToByteArray()
    )?.decodeToString().toString()
}

suspend fun getAllSubscribers(
    onSuccess: (ApiListResponse) -> Unit,
    onError: (Exception) -> Unit
){
    try {
        val result = window.api.tryGet(
            apiPath = "getallsubscribers"
        )?.decodeToString()
        if(result != null){
            onSuccess(Json.decodeFromString(result))
        } else {
            onError(Exception("Something went wrong"))
        }
    }catch (e: Exception){
        println(e)
        onError(e)
    }
}