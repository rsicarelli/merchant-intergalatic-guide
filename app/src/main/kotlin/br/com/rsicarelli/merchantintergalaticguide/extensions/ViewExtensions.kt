package br.com.rsicarelli.merchantintergalaticguide.extensions

import android.view.View

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}