package com.example.weatherapplication.compose

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapplication.utils.Constants.APP_NAME
import com.example.weatherapplication.utils.Constants.LAST_SEARCH_KEY

/**
 * @author Kunal Rathod
 * @since 20 Mar 2023
 * This class hold the last search history in shared preferences
 * */
class PrefManager(val context : Context) {

    /*
    this function save the last key search in searchBox
    * */
    fun saveLastSearch(lastSearch : String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(LAST_SEARCH_KEY, lastSearch)
        editor.commit()
    }

    /*
    this function give the last search key what user search in searchBox
    * */

    fun getLastSearch(): String? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(LAST_SEARCH_KEY, "")
    }
}