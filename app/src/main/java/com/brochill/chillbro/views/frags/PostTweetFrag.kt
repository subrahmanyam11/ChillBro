package com.brochill.chillbro.views.frags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.brochill.chillbro.R
import com.brochill.chillbro.databinding.FragmentPostTweetBinding
import com.brochill.chillbro.mvm.TweetsModel
import com.brochill.chillbro.mvm.TweetsViewModel

class PostTweetFrag : Fragment(R.layout.fragment_post_tweet) {

    private lateinit var binding: FragmentPostTweetBinding
    private val tweetViewModel: TweetsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostTweetBinding.bind(view)

        binding.apply {

            postNewTweetFrag.setOnClickListener {
                if (msgNewTweetFrag.text.isEmpty()) return@setOnClickListener
                tweetViewModel.addTweet(requireContext(), msgNewTweetFrag.text.toString().trim())
                msgNewTweetFrag.setText("")
                findNavController().popBackStack()
            }



        }


    }
}