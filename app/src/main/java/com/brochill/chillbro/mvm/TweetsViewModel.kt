package com.brochill.chillbro.mvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TweetsViewModel: ViewModel() {

    private val _tweetsList = MutableLiveData<List<TweetsModel>>()
    private val _loadingTweet = MutableLiveData<Boolean>(false)
    var tweetsList: MutableLiveData<List<TweetsModel>> = _tweetsList
    var loadingTweet: MutableLiveData<Boolean> = _loadingTweet


    private val _addingTweet = MutableLiveData<Boolean>(false)
    var addingTweet: MutableLiveData<Boolean> = _addingTweet

    fun getTweets(){
//        implement server call interface

        val list = mutableListOf<TweetsModel>()
        list.add(TweetsModel("hello", "1", "1.0.3"))
        list.add(TweetsModel("world", "2", "1.0.3"))
        list.add(TweetsModel("!", "3", "1.0.3"))
        if (_tweetsList.value == null) _tweetsList.value = list

    }

    fun addTweet(tweet: TweetsModel){

        // implement server call interface

        val list = mutableListOf<TweetsModel>()
        if (_tweetsList.value != null){
            list.addAll(_tweetsList.value!!)
        }
        list.add(tweet)
        _tweetsList.value = list
    }


}