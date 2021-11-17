package com.twmaze.util.livedata

import androidx.lifecycle.MutableLiveData

fun <T> liveData(): Lazy<MutableLiveData<T>> {
    return LazyMutableLiveData()
}

fun <T> liveEvent(): Lazy<LiveEvent<T>> {
    return LazyLiveEvent()
}