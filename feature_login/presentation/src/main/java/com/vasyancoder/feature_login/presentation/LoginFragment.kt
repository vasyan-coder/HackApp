package com.vasyancoder.feature_login.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vasyancoder.feature_login.domain.use_case.AuthenticateUserUseCase
import com.vasyancoder.feature_login.presentation.databinding.FragmentLoginBinding
import kotlin.math.log

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogIn.setOnClickListener {
            viewModel.authenticateUser(
                login = binding.textInputLoginEditText.text.toString(),
                password = binding.textInputPasswordEditText.text.toString()
            )
        }

        viewModel.authenticationResult.observe(viewLifecycleOwner) {
            if (it == AuthenticateUserUseCase.AuthenticationResult.Success) {
                Log.d(TAG, it.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val TAG = "LoginFragment"
    }
}