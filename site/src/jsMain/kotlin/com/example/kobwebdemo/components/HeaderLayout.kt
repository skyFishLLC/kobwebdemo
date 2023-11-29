package com.example.kobwebdemo.components

import androidx.compose.runtime.Composable
import com.example.kobwebdemo.navigation.Screen
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px

@Composable
fun HeaderLayout(
    context: PageContext,
    content: @Composable () -> Unit
){
    Box(
       modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .background(Colors.Navy)
                //.padding(20.px)
                .fillMaxWidth()
                .height(150.px),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement =  Arrangement.Start
        ) {
            SpanText(
                text = "Home Page",
                modifier = Modifier
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .margin(40.px)
                    .fontSize(24.px)
                    .onClick { context.router.navigateTo(Screen.HomePage.route) }
            )
            SpanText(
                text = "Subscriber List",
                modifier = Modifier
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .margin(40.px)
                    .fontSize(24.px)
                    .onClick { context.router.navigateTo(Screen.SubscriberListPage.route) }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .margin(top = 150.px),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}