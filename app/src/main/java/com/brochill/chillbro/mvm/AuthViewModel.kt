package com.brochill.chillbro.mvm

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.brochill.chillbro.shared_perf.User
import org.json.JSONObject


class AuthViewModel : ViewModel() {

    val userObj = User()
    private val _result = MutableLiveData(false)
    var result: MutableLiveData<Boolean> = _result

    private val _createUser = MutableLiveData(false)
    var createUser: MutableLiveData<Boolean> = _createUser

    fun loginUser(context: Context, user: String, pass: String) {

        // implement server call interface..
        val loginUrl = "https://wern-api.brochill.app/login"

        // Create JSON
        val userCredentialsObj = JSONObject()
        userCredentialsObj.put("email", user)
        userCredentialsObj.put("password", pass)

        val queue = Volley.newRequestQueue(context.applicationContext)
        val request = JsonObjectRequest(Request.Method.POST, loginUrl, userCredentialsObj, { response ->

            try {
                if (response.getString("token").length > 2) {
                    userObj.saveUser(
                        context,
                        response.getString("first_name"),
                        response.getString("last_name"),
                        response.getString("_id"),
                        response.getString("email"),
                        response.getString("token")
                    )

                    _result.value = true
                }
            } catch (e: Exception) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show()
                Log.e("TAG", "createUser: ${e.message}")
            }
        }, { error ->
            Log.d("TAG", "response: $error")
        })
        queue.add(request)

    }

    fun createUser(context: Context, first: String, last: String, user: String, pass: String) {

        // implement server call interface..

        val YourUrl = "https://wern-api.brochill.app/register"

        // Create JSON
        val userDetailsObj = JSONObject()
        userDetailsObj.put("first_name", first)
        userDetailsObj.put("last_name", last)
        userDetailsObj.put("email", user)
        userDetailsObj.put("password", pass)

        val queue = Volley.newRequestQueue(context.applicationContext)
        val request = JsonObjectRequest(Request.Method.POST, YourUrl, userDetailsObj, { response ->


            try {
                val result = response
                if (result.getString("token").length > 2) {
                    userObj.saveUser(
                        context,
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("_id"),
                        result.getString("email"),
                        result.getString("token")
                    )
                    Toast.makeText(context, "User created Successfully", Toast.LENGTH_SHORT).show()
                    _createUser.value = true
                }
            } catch (e: Exception) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show()
                Log.e("TAG", "createUser: ${e.message}")
            }
        }, { error ->
            Log.d("TAG", "response: ${error.message}")
        })
        queue.add(request)

    }
}