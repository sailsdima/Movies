package com.twmaze.util.livedata

class LazyLiveEvent<T> : Lazy<LiveEvent<T>> {

    private var cached: LiveEvent<T>? = null

    override val value: LiveEvent<T>
        get() = cached ?: LiveEvent<T>().also {
            cached = it
        }

    override fun isInitialized(): Boolean {
        return cached != null
    }

}