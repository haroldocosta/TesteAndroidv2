package com.haroldocosta.bankapp.utils.extensions

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun RecyclerView?.setLinearLayout(
    orientation: Int = RecyclerView.VERTICAL,
    hasFixedSize: Boolean = false,
    isReverse: Boolean = false
) {
    this?.apply {
        setHasFixedSize(hasFixedSize)
        layoutManager = LinearLayoutManager(this.context, orientation, isReverse)
    }
}