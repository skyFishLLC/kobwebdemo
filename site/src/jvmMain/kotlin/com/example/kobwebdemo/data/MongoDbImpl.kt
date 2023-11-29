package com.example.kobwebdemo.data

import com.example.kobwebdemo.model.Subscriber
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import kotlinx.coroutines.flow.toList

@InitApi
fun initializeMongo(context: InitApiContext){
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    context.data.add(MongoDbImpl())
}

class MongoDbImpl(): MongoDb {
    private val client = MongoClient.create()
    private val database = client.getDatabase("kobwebdemo")
    private val subscriberCollection = database.getCollection<Subscriber>("subscriber")



    override suspend fun getAllSubscribers(): List<Subscriber> {
        return subscriberCollection.find().toList()
    }

    override suspend fun addSubscriber(subscriber: Subscriber): Boolean {
        return subscriberCollection.insertOne(subscriber).wasAcknowledged()
    }
}