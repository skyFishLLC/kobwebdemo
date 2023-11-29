package com.example.kobwebdemo.model

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject


@Serializable(ListResponseSerializer::class)
sealed class ApiListResponse {
    @Serializable
    @SerialName("loading")
    object Loading: ApiListResponse()
    @Serializable
    @SerialName("success")
    data class Success(val data: List<Subscriber>) : ApiListResponse()
    @Serializable
    @SerialName("error")
    data class Error(val message: String): ApiListResponse()
}

object ListResponseSerializer: JsonContentPolymorphicSerializer<ApiListResponse>(ApiListResponse::class){
    override fun selectDeserializer(element: JsonElement) = when {
        "data" in element.jsonObject -> ApiListResponse.Success.serializer()
        "message" in element.jsonObject -> ApiListResponse.Error.serializer()
        else -> ApiListResponse.Loading.serializer()
    }
}