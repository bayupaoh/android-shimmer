package me.bayupaoh.helper

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by DODYDMW19 on 7/26/2017.
 */

class PreferenceManager {

    private var mApplicationContext: Context? = null

    var isLogin: Boolean
        get() {
            val preferences = mApplicationContext!!.getSharedPreferences(NAME, Context.MODE_PRIVATE)
            return preferences.getBoolean(KEY_IS_LOGIN, false)
        }
        set(isLogin) {
            val preferences = mApplicationContext!!.getSharedPreferences(NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putBoolean(KEY_IS_LOGIN, isLogin)
            editor.apply()
        }

    companion object {

        private var sManager: PreferenceManager? = null
        private val NAME = "me.bayupaoh"
        private val KEY_IS_LOGIN = "login"

        fun instance(): PreferenceManager {
            if (sManager == null) {
                sManager = PreferenceManager()
            }
            return sManager as PreferenceManager
        }
    }

}
