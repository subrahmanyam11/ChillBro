package com.brochill.chillbro.views.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.brochill.chillbro.R
import com.brochill.chillbro.databinding.FragmentLoginBinding
import com.brochill.chillbro.mvm.AuthViewModel
import com.brochill.chillbro.shared_perf.User

class LoginFrag : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val authViewModel: AuthViewModel by activityViewModels()
    private val user = User()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.apply {


            nextLoginFrag.setOnClickListener {
                if (mailLoginFrag.text.isEmpty()){
                    mailLoginFrag.error = "Must fill"
                    return@setOnClickListener
                }
                if (passwordLoginFrag.text.isEmpty()){
                    passwordLoginFrag.error = "Enter password"
                    return@setOnClickListener
                }
                pbLoginFrag.isVisible = true
                authViewModel.loginUser(requireContext(), mailLoginFrag.text.toString().trim(), passwordLoginFrag.text.toString().trim())
          }


            createAccountLoginFrag.setOnClickListener { findNavController().navigate(R.id.action_loginFrag_to_createUserFrag) }

        }

        authViewModel.result.observe(viewLifecycleOwner){
            if (it && user.userTocken(requireContext()) != "null") findNavController().navigate(R.id.tweetsFrag)
        }
    }


}