package com.twmaze.util.livedata

import androidx.lifecycle.MutableLiveData

class LazyMutableLiveData<T> : Lazy<MutableLiveData<T>> {

    private var cached: MutableLiveData<T>? = null

    override val value: MutableLiveData<T>
        get() = cached ?: MutableLiveData<T>().also {
            cached = it
        }

    override fun isInitialized(): Boolean {
        return cached != null
    }

}