package com.task.noteapp.utility

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showShortSnack(message:String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()

}