package com.geektech.taskmanager.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.geektech.taskmanager.databinding.FragmentAuthBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import com.geektech.taskmanager.utils.makeToast
import com.geektech.taskmanager.R


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var storedVerificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var smsCode:String
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSendCode.setOnClickListener{
            sendCode()
        }

        binding.btnCheckCode.setOnClickListener{
            checkCode()
        }


    }

    private fun checkCode() {
        binding.btnCheckCode.setOnClickListener{
            smsCode = binding.etCode.text.toString()
            val credential = PhoneAuthProvider.getCredential(storedVerificationId, smsCode)

            auth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity()) {task->
                    if (task.isSuccessful) {
                        Log.d("aga", "signInWithCredential:success")
                        findNavController().navigate(R.id.navigation_home)
                    }
                }
        }
    }


    private fun sendCode() {
        initCallback()
        var phoneNumber:String = binding.etPhoneNumber.text.toString()

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun initCallback() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                auth.signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                requireActivity().makeToast(e.toString())

                if (e is FirebaseAuthInvalidCredentialsException) {
                    requireActivity().makeToast(e.toString())

                } else if (e is FirebaseTooManyRequestsException) {
                    requireActivity().makeToast(e.toString())
                }

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId
                resendToken = token

                binding.etPhoneNumber.visibility = View.GONE
                binding.btnSendCode.visibility = View.GONE

                binding.etCode.visibility = View.VISIBLE
                binding.btnCheckCode.visibility = View.VISIBLE
            }
        }

    }
}