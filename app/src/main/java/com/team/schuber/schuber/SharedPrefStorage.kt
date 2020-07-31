package com.team.schuber.schuber

import android.content.Context
import android.content.SharedPreferences

class SharedPrefStorage(val context: Context) {

    fun saveKey(key: Int) =
        getPref(context).edit().let {
            it.putInt("verificationId", key)
            it.apply()
        }

    fun getKey(): Int =
        getPref(context).getInt("verificationId", 0)

    fun removeKey() =
        getPref(context).edit().let {
            it.remove("verificationId")
            it.apply()
        }

    fun savePhone(key: String) =
        getPref(context).edit().let {
            it.putString("phone", key)
            it.apply()
        }

    fun getPhone(): String? =
        getPref(context).getString("phone", "")

    fun removePhone() =
        getPref(context).edit().let {
            it.remove("phone")
            it.apply()
        }

    fun saveToken(token: String, access: Boolean) =
        getPref(context).edit().let {
            it.putString(getKey(access), token)
            it.apply()
        }

    fun getToken(isAccess: Boolean): String =
        "Bearer " + getPref(context).getString(getKey(isAccess), "")

    fun removeToken() =
        getPref(context).edit().let {
            it.remove(getKey(true))
            it.apply()
        }

    private fun getPref(context: Context): SharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)

    private fun getKey(isAccess: Boolean): String = if (isAccess) "Access" else "Refresh"

}
