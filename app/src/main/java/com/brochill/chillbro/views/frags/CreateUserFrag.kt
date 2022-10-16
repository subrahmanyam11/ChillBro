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
import com.brochill.chillbro.databinding.FragmentCreateUserBinding
import com.brochill.chillbro.mvm.AuthViewModel
import com.brochill.chillbro.shared_perf.User

class CreateUserFrag : Fragment(R.layout.fragment_create_user) {

    private lateinit var binding: FragmentCreateUserBinding

    private val authViewModel: AuthViewModel by activityViewModels()
    val user = User()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateUserBinding.bind(view)


        binding.apply {

            nextSignUpFrag.setOnClickListener {
                if (firstSignUpFrag.text.isEmpty()){
                    firstSignUpFrag.error = "Must fill"
                    return@setOnClickListener
                }
                if (lastSignUpFrag.text.isEmpty()){
                    lastSignUpFrag.error = "Must fill"
                    return@setOnClickListener
                }
                if (mailSignUpFrag.text.isEmpty()){
                    mailSignUpFrag.error = "Must fill"
                    return@setOnClickListener
                }
                if (passSignUpFrag.text.isEmpty()){
                    passSignUpFrag.error = "Must fill"
                    return@setOnClickListener
                }
                pbSignUpFrag.isVisible = true
                authViewModel.createUser(requireContext(), firstSignUpFrag.text.toString().trim(), lastSignUpFrag.text.toString().trim(), mailSignUpFrag.text.toString().trim(), passSignUpFrag.text.toString().trim())
         }

            authViewModel.createUser.observe(viewLifecycleOwner){
                if (it && user.userTocken(requireContext())!= "null") findNavController().navigate(R.id.action_createUserFrag_to_welcomeFrag)
            }


        }
    }
}