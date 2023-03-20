package com.example.weatherapplication.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author Kunal Rathod
 * @since 20 March 2023
 * this class hold the logic for hiding keyboard
 * */
fun Activity.hideKeyboard(){
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}