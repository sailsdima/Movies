package com.twmaze.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openLink(link: String) {
    val openURL = linkIntent(link)
        startActivity(openURL)
}

fun linkIntent(link: String) = Intent(Intent.ACTION_VIEW, Uri.parse(link))
