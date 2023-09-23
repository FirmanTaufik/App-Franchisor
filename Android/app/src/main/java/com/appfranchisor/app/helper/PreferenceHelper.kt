package com.appfranchisor.app.helper

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private var mySharedPreferences: SharedPreferences? = null
    private const val PREF = "pref"
    private const val FIRST_TIME = "FIRST_TIME"
    private const val ROLE= "ROLE"
    private const val TOKEN= "TOKEN"

    fun clearAllPreference(context: Context) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.clear()
        myEditor.commit()
    }

    fun getToken(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(TOKEN, null)
    }



    fun setToken(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(TOKEN, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getFirstTime(context: Context): Boolean {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getBoolean(FIRST_TIME, true)
    }



    fun setRole(context: Context, value: Int?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putInt(ROLE, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getRole(context: Context): Int? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getInt(ROLE,1)
    }



}
