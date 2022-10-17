package com.brochill.chillbro.shared_perf

import android.content.Context
import android.content.SharedPreferences

class User {

    val userSaved = "com.chillbro.user"

    private val FIRST_NAME = "first_name"
    private val LAST_NAME = "last_name"
    private val USER_ID = "user_id"
    private val USER_EMAIL = "user_email"
    private val TOKEN = "token"


    // setter methods..
    fun saveUser(context: Context, first: String, last:String, id:String, mail:String, tocken:String){

        val sharePref: SharedPreferences? = context.getSharedPreferences(userSaved, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharePref!!.edit()
        editor.putString(FIRST_NAME, first)
        editor.putString(LAST_NAME, last)
        editor.putString(USER_ID, id)
        editor.putString(USER_EMAIL, mail)
        editor.putString(TOKEN, tocken)
        editor.apply()

    }


    // getter methods..
    fun userTocken(context: Context): String {
        val sharePref: SharedPreferences? = context.getSharedPreferences(userSaved, Context.MODE_PRIVATE)
        return sharePref?.getString(TOKEN, "null")!!
    }
    fun userId(context: Context): String {
        val sharePref: SharedPreferences? = context.getSharedPreferences(userSaved, Context.MODE_PRIVATE)
        return sharePref?.getString(USER_ID, "null")!!
    }
    fun userMail(context: Context): String {
        val sharePref: SharedPreferences? = context.getSharedPreferences(userSaved, Context.MODE_PRIVATE)
        return sharePref?.getString(USER_EMAIL, "null")!!
    }

    // logout..
    fun LogoutUser(context: Context) {
        val sharePref: SharedPreferences? = context.getSharedPreferences(userSaved, Context.MODE_PRIVATE)
        val editor = sharePref!!.edit()
        editor.clear()
        editor.apply()
    }

}