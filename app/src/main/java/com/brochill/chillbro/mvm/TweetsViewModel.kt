package com.brochill.chillbro.mvm

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.brochill.chillbro.shared_perf.User
import org.json.JSONArray
import org.json.JSONObject


class TweetsViewModel : ViewModel() {

    val userObj = User()

    private val _tweetsList = MutableLiveData<List<TweetsModel>>()
    private val _loadingTweet = MutableLiveData<Boolean>(false)
    private val _paginateCount = MutableLiveData(0)
    var tweetsList: MutableLiveData<List<TweetsModel>> = _tweetsList
    var loadingTweet: MutableLiveData<Boolean> = _loadingTweet


    private val _addingTweet = MutableLiveData<Boolean>(false)
    var addingTweet: MutableLiveData<Boolean> = _addingTweet

    fun getTweets(context: Context) {
        _paginateCount.value = 0
        val YourUrl = "https://wern-api.brochill.app/tweets"

        val request: StringRequest = object : StringRequest(
            Method.GET, YourUrl,
            Response.Listener { response ->
                if (response != null) {
                    val tweetResponse = JSONArray(response)
                    val list = mutableListOf<TweetsModel>()
                    for (i in 0..tweetResponse.length() - 1) {
                        val tweet = tweetResponse.getJSONObject(i)
                        list.add(
                            TweetsModel(
                                tweet.getString("tweet"),
                                tweet.getString("_id"),
                                tweet.getString("__v")
                            )
                        )
                    }
                    _tweetsList.value = list
                } else {
                    Log.e("Your Array Response", "Data Null")
                }
            },
            Response.ErrorListener { error -> Log.e("error is ", "" + error) }) {
            //This is for Headers If You Needed
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["x-api-key"] = userObj.userTocken(context)
                return params
            }

        }
        val queue = Volley.newRequestQueue(context.applicationContext)
        queue.add(request)


    }

    fun paginateTweets(context: Context) {

        _paginateCount.value = _paginateCount.value!! + 1
        val YourUrl = "https://wern-api.brochill.app/tweets?page=${_paginateCount.value}"

        val request: StringRequest = object : StringRequest(
            Method.GET, YourUrl,
            Response.Listener { response ->
                if (response != null) {
                    val tweetResponse = JSONArray(response)
                    val list = mutableListOf<TweetsModel>()
                    for (i in 0..tweetResponse.length() - 1) {
                        val tweet = tweetResponse.getJSONObject(i)
                        list.add(
                            TweetsModel(
                                tweet.getString("tweet"),
                                tweet.getString("_id"),
                                tweet.getString("__v")
                            )
                        )
                    }
                    if (_tweetsList.value != null) {
                        list.addAll(_tweetsList.value!!)
                    }
                    _tweetsList.value = list
                } else {
                    Log.e("Your Array Response", "Data Null")
                }
            },
            Response.ErrorListener { error -> Log.e("error is ", "" + error) }) {
            //This is for Headers If You Needed
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["x-api-key"] = userObj.userTocken(context)
                return params
            }

        }
        request.setRetryPolicy(
            DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        )
        val queue = Volley.newRequestQueue(context.applicationContext)
        queue.add(request)

    }

    fun addTweet(context: Context, msg: String) {

        // implement server call interface

        val addTweetUrl = "https://wern-api.brochill.app/tweets"


        // Create JSON
        val tweetObj = JSONObject()
        tweetObj.put("tweet", msg)

        val queue = Volley.newRequestQueue(context.applicationContext)
        val request :JsonObjectRequest = object : JsonObjectRequest(Method.POST,
            addTweetUrl,
            tweetObj,
            Response.Listener { response ->
                Log.e("TAG", "addTweet: $response", )
                try {
                    val list = mutableListOf<TweetsModel>()
                    if (_tweetsList.value != null) {
                        list.addAll(_tweetsList.value!!)
                    }
                    list.add(
                        TweetsModel(
                            response.getString("tweet"),
                            response.getString("_id"),
                            response.getString("__v")
                        )
                    )
                    Toast.makeText(context, "Posted successfully", Toast.LENGTH_SHORT).show()
                    _tweetsList.value = list
                }catch (e:Exception){
                    Log.e("response: ", "addTweet: $e", )
                }
            },
            Response.ErrorListener{ error ->

                Log.e("Response", "addTweet: $error", )

            }) {

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["x-api-key"] = userObj.userTocken(context)
                return params
            }

        }

        queue.add(request)



    }


}