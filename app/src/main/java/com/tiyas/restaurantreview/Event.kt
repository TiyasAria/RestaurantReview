package com.tiyas.restaurantreview

import android.content.Context

open class Event<out T>(private val content : T) {
    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
    private set

    fun getContentIfNotHandled() : T? {
        return  if (hasBeenHandled){
            null
        } else{
            hasBeenHandled = true
            content
        }
    }

    fun peekContent() : T = content
}