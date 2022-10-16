package com.brochill.chillbro.mvm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brochill.chillbro.shared_perf.User

class AuthViewModel: ViewModel() {

    val userObj = User()
    private val _result = MutableLiveData(false)
    var result:MutableLiveData<Boolean> = _result

    private val _createUser = MutableLiveData(false)
    var createUser: MutableLiveData<Boolean> = _createUser

    fun loginUser(context: Context, user: String, pass: String){

        // implement server call interface..
        // false fun()

        // true..(result)
        userObj.saveUser(context, "hello", "world", "1", user, "aldfkjaoiffjjdfjlkf")
        _result.value = true
    }

    fun createUser(context: Context, first: String, last: String, user: String, pass: String){

        // implement server call interface..
        // false fun()

        //true..(result)
        userObj.saveUser(context, first, last, "1", user, "aldfkjaoiffjjdfjlkf")
        _createUser.value = true
    }
}