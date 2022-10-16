package com.brochill.chillbro.views.frags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.brochill.chillbro.R
import com.brochill.chillbro.adapters.TweetsAdapter
import com.brochill.chillbro.databinding.FragmentTweetsBinding
import com.brochill.chillbro.mvm.TweetsModel
import com.brochill.chillbro.mvm.TweetsViewModel
import com.brochill.chillbro.shared_perf.User

class TweetsFrag : Fragment(R.layout.fragment_tweets), TweetsAdapter.AdapterItemClickListener {

    private lateinit var binding: FragmentTweetsBinding
    private lateinit var tweetsAdatper: TweetsAdapter
    private val user = User()

    private val tweetViewModel: TweetsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTweetsBinding.bind(view)

        // check user is login or not..
        if (user.userTocken(requireContext()) == "null") findNavController().navigate(R.id.loginFrag)

        tweetsAdatper = TweetsAdapter(this)

        binding.apply {

            // remove//////////////////
            toolbar.setOnClickListener{
                user.LogoutUser(requireContext())
                if (user.userTocken(requireContext()) == "null") findNavController().navigate(R.id.action_tweetsFrag_to_loginFrag)
            }

            recViewTweetsFrag.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = tweetsAdatper

            }

            floatAddTweetsFrag.setOnClickListener { findNavController().navigate(R.id.action_tweetsFrag_to_postTweetFrag) }

            tweetViewModel.tweetsList.observe(viewLifecycleOwner){
                tweetsAdatper.submitList(it)
                Log.e("TAG", "onViewCreated: $it", )
            }

            tweetViewModel.getTweets()

        }




    }

    override fun onTweetClick(titleModel: TweetsModel, position: Int) {
    }
}