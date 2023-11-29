package com.example.kobwebdemo.api

import com.example.kobwebdemo.data.MongoDbImpl
import com.example.kobwebdemo.model.ApiListResponse
import com.example.kobwebdemo.model.Subscriber
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import org.bson.codecs.ObjectIdGenerator


@Api(routeOverride = "addsubscriber")
suspend fun addSubscriber(context: ApiContext){
    try {
        val subscriber = context.req.body?.decodeToString()?.let { Json.decodeFromString<Subscriber>(it) }
        val newSubscriber = subscriber?.copy(_id = ObjectIdGenerator().generate().toString())
        var responseBody = Json.encodeToString(newSubscriber?.let { context.data.getValue<MongoDbImpl>().addSubscriber(it) })
        context.res.setBodyText(responseBody)
    }catch (e: Exception){
        context.res.setBodyText(Json.encodeToString(e))
    }
}

@Api(routeOverride = "getallsubscribers")
suspend fun getAllSubscribers(context: ApiContext){
    try {
        val myUsers = context.data.getValue<MongoDbImpl>().getAllSubscribers()
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Success(data = myUsers)))
    }catch (e: Exception){
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Error(message = e.message.toString())))
    }
}