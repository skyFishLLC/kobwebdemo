package com.example.kobwebdemo.pages.subscribers

import androidx.compose.runtime.*
import com.example.kobwebdemo.components.HeaderLayout
import com.example.kobwebdemo.data.getAllSubscribers
import com.example.kobwebdemo.model.ApiListResponse
import com.example.kobwebdemo.model.Subscriber
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun SubscribersListPage(){
    val context = rememberPageContext()
    var response by remember {  mutableStateOf<ApiListResponse>(ApiListResponse.Loading) }

    LaunchedEffect(Unit){
        getAllSubscribers(
            onSuccess = {
                if(it is ApiListResponse.Success){
                    response = it
                }
            },
            onError = {
                println("Error")
                response = ApiListResponse.Error(it.message.toString())
            }
        )
    }

    HeaderLayout(context = context){
        Column(
            modifier = Modifier
                .margin(40.px)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            when (response){
                is ApiListResponse.Success -> {
                    (response as ApiListResponse.Success).data.forEach { subscriber ->
                        SpanText(
                            text = subscriber.email,
                            modifier = Modifier
                                .fontSize(24.px)
                                .margin(12.px)
                        )
                    }
                }
                else -> {
                    println("Nothing to display")
                }
            }
        }
    }
}