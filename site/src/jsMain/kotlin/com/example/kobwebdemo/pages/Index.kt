package com.example.kobwebdemo.pages

import androidx.compose.runtime.*
import com.example.kobwebdemo.components.HeaderLayout
import com.example.kobwebdemo.data.addSubscriber
import com.example.kobwebdemo.model.Subscriber
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLInputElement
import kotlin.js.Date

@Page
@Composable
fun HomePage() {
    val context = rememberPageContext()
    val scope = rememberCoroutineScope()

    HeaderLayout(
        context = context
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            SpanText(
                text = "Subscribe to our Newsletter!",
                modifier = Modifier
                    .fontSize(30.px)
                    .margin(20.px)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Input(
                    type = InputType.Text,
                    attrs = Modifier
                        .id("emailInput")
                        .width(320.px)
                        .height(50.px)
                        .color(Colors.Navy)
                        .backgroundColor(Colors.LightGray)
                        .padding(24.px)
                        .fontSize(24.px)
                        .borderRadius(100.px)
                        .margin(right = 50.px)
                        .outline(width = 0.px)
                        .border(width = 0.px)
                        .toAttrs{
                            attr("placeholder", "Enter Your Email")
                        }
                )

                Button(
                    attrs = Modifier
                        .onClick {
                            val email = (document.getElementById("emailInput") as HTMLInputElement).value
                            if(validateEmail(email)){
                                scope.launch {
                                    addSubscriber(
                                        Subscriber(
                                            email = email,
                                            date = Date.now().toLong()
                                        )
                                    )
                                }
                            }
                        }
                        .height(54.px)
                        .backgroundColor(Colors.Navy)
                        .borderRadius(100.px)
                        .padding(leftRight = 50.px)
                        .cursor(Cursor.Pointer)
                        .toAttrs()
                ){
                    SpanText(
                        modifier = Modifier
                            .fontSize(18.px)
                            .fontWeight(FontWeight.Medium)
                            .color(Colors.White),
                        text = "Subscribe"
                    )
                }


            }
        }


    }



}

fun validateEmail(email: String): Boolean {
    val regex = "^[A-Za-z0-9_.+-]+@[A-Za-z0-9-]+\\.[A-Za-z0-9-.]+$"
    return regex.toRegex().matches(email)
}
