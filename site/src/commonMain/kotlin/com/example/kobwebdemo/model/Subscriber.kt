package com.example.kobwebdemo.model

import kotlinx.serialization.Serializable

@Serializable
data class Subscriber(
    val _id: String = "",
    val date: Long = 0L,
    val email: String = ""
)
