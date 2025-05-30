package com.example.challenge


sealed class Screen(val route:String) {
    object BookScreen:Screen("bookscreen")
    object DetailScreen:Screen("detailscreen")

}