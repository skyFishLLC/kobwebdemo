package com.example.kobwebdemo.navigation

sealed class Screen(val route: String) {
    object HomePage: Screen(route = "/")
    object SubscriberListPage: Screen(route = "/subscribers")
}