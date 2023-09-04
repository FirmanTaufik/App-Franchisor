package com.appfranchisor.app.helper

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private var mySharedPreferences: SharedPreferences? = null
    private const val PREF = "pref"
    private const val USERNAME = "USERNAME"
    private const val USER_PASSWORD = "PASSWORD"
    private const val REMEMBER = "REMEMBER"
    private const val FIRST_TIME = "FIRST_TIME"
    private const val USER_ID= "USER_ID"
    private const val NM_KURIR= "NM_KURIR"
    private const val ROLE= "ROLE"

    fun clearAllPreference(context: Context) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.clear()
        myEditor.commit()
    }

    fun getFirstTime(context: Context): Boolean {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getBoolean(FIRST_TIME, true)
    }

    fun setRemember(context: Context, value: Boolean?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        if (value != null) {
            myEditor.putBoolean(REMEMBER, value)
        }
        myEditor.commit()
        myEditor.apply()
    }

    fun getRemember(context: Context): Boolean? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getBoolean(REMEMBER, false)
    }

    fun setPassword(context: Context, id: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(USER_PASSWORD, id)
        myEditor.commit()
        myEditor.apply()
    }

    fun getPassword(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(
            USER_PASSWORD,
            null
        )
    }

    fun setUsername(context: Context, id: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(USERNAME, id)
        myEditor.commit()
        myEditor.apply()
    }



    fun setUserId(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(USER_ID, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getUserId(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(USER_ID,null)
    }

    fun setNmKurir(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(NM_KURIR, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getNmKurir(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(NM_KURIR,null)
    }


    fun setRole(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(ROLE, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getRole(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(ROLE,null)
    }



}
