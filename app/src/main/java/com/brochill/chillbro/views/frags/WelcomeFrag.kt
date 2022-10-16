package com.brochill.chillbro.views.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.brochill.chillbro.R
import com.brochill.chillbro.databinding.FragmentWelcomeBinding
import com.brochill.chillbro.shared_perf.User


class WelcomeFrag : Fragment(R.layout.fragment_welcome) {

    private lateinit var binding: FragmentWelcomeBinding
    private val user = User()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)


        binding.startWelcomeFrag.setOnClickListener{findNavController().navigate(R.id.action_welcomeFrag_to_tweetsFrag)}


    }
}