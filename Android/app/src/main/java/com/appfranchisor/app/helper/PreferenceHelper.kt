package com.app.legatranskurir.helper

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private var mySharedPreferences: SharedPreferences? = null
    private const val PREF = "pref"
    private const val USERNAME = "USERNAME"
    private const val USER_PASSWORD = "PASSWORD"
    private const val REMEMBER = "REMEMBER"
    private const val FIRST_TIME = "FIRST_TIME"
    private const val ID_KURIR = "ID_KURIR"
    private const val ID_CABANG= "ID_CABANG"
    private const val IS_BIOMETRIC_ON = "IS_BIOMETRIC_ON"
    private const val USER_ID= "USER_ID"
    private const val NM_KURIR= "NM_KURIR"
    private const val NO_HP= "NO_HP"
    private const val NM_CABANG= "NM_CABANG"
    private const val NM_ASAL= "NM_ASAL"
    private const val ROLE= "ROLE"
    private const val LOGIN_MODEL= "LOGIN_MODEL"


    private const val BlUETOOTH_ADDRESS_DEVICE = "BlUETOOTH_ADDRESS_DEVICE"
    private const val BlUETOOTH_ADDRESS_DEVICE_RESI = "BlUETOOTH_ADDRESS_DEVICE_RESI"


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

    fun getUsername(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(USERNAME, null)
    }

    fun setIsBiometricOn(context: Context, value: Boolean?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putBoolean(IS_BIOMETRIC_ON, value!!)
        myEditor.commit()
        myEditor.apply()
    }


    fun getIsBiometricOn(context: Context): Boolean? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getBoolean(IS_BIOMETRIC_ON, false)
    }



    fun setIdKurir(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(ID_KURIR, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getIdKurir(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(ID_KURIR,null)
    }

    fun setIdAsal(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(ID_CABANG, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getIdAsal(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(ID_CABANG,null)
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

    fun setNoHp(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(NO_HP, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getNoHp(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(NO_HP,null)
    }

    fun setNmCabang(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(NM_CABANG, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getNmCabang(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(NM_CABANG,null)
    }

    fun setNmAsal(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(NM_ASAL, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getNmAsal(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(NM_ASAL,null)
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

    fun setBuetoothAddressDeviceResi(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(BlUETOOTH_ADDRESS_DEVICE_RESI, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getBuetoothAddressDeviceResi(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(BlUETOOTH_ADDRESS_DEVICE_RESI,null)
    }

    fun setBuetoothAddressDevice(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(BlUETOOTH_ADDRESS_DEVICE, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getBuetoothAddressDevice(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(BlUETOOTH_ADDRESS_DEVICE,null)
    }


    fun setLoginModel(context: Context, value: String?) {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        val myEditor = mySharedPreferences!!.edit()
        myEditor.putString(LOGIN_MODEL, value!!)
        myEditor.commit()
        myEditor.apply()
    }

    fun getLoginModel(context: Context): String? {
        mySharedPreferences = context.getSharedPreferences(
            PREF, Context.MODE_PRIVATE
        )
        return mySharedPreferences!!.getString(LOGIN_MODEL,null)
    }

}
