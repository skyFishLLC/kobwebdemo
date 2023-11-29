package com.example.kobwebdemo.data

import com.example.kobwebdemo.model.Subscriber

interface MongoDb {

    suspend fun getAllSubscribers(): List<Subscriber>
    suspend fun addSubscriber(subscriber: Subscriber): Boolean

}