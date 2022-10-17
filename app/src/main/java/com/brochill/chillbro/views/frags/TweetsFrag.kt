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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.brochill.chillbro.R
import com.brochill.chillbro.adapters.TweetsAdapter
import com.brochill.chillbro.databinding.FragmentTweetsBinding
import com.brochill.chillbro.mvm.TweetsModel
import com.brochill.chillbro.mvm.TweetsViewModel
import com.brochill.chillbro.shared_perf.User

class TweetsFrag : Fragment(R.layout.fragment_tweets), TweetsAdapter.AdapterItemClickListener {

    private lateinit var binding: FragmentTweetsBinding
    private lateinit var tweetsLLM: LinearLayoutManager
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
            logoutTweetsFrag.setOnClickListener{
                user.LogoutUser(requireContext())
                if (user.userTocken(requireContext()) == "null") findNavController().navigate(R.id.action_tweetsFrag_to_loginFrag)
            }

            tweetsLLM = LinearLayoutManager(requireContext())
            recViewTweetsFrag.apply {
                setHasFixedSize(true)
                layoutManager = tweetsLLM
                adapter = tweetsAdatper

            }

            floatAddTweetsFrag.setOnClickListener { findNavController().navigate(R.id.action_tweetsFrag_to_postTweetFrag) }

            tweetViewModel.tweetsList.observe(viewLifecycleOwner){
                tweetsAdatper.submitList(it)
            }

            tweetViewModel.getTweets(requireContext())

            recViewTweetsFrag.addOnScrollListener(object : OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
//                        if(tweetsLLM.findLastCompletelyVisibleItemPosition() == tweetsLLM.itemCount-2) tweetViewModel.paginateTweets(requireContext())
                }
            })

        }




    }

    override fun onTweetClick(titleModel: TweetsModel, position: Int) {
    }
}