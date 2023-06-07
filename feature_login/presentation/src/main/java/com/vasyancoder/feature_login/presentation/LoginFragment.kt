package com.vasyancoder.feature_login.presentation

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vasyancoder.feature_login.domain.use_case.AuthenticateUserUseCase
import com.vasyancoder.feature_login.presentation.databinding.FragmentLoginBinding
import com.vasyancoder.navigation.navigate

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

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
        binding.textViewRegistration.setOnClickListener {
            val extras: Navigator.Extras = FragmentNavigatorExtras(
                binding.buttonLogIn to
                        getString(com.vasyancoder.core.R.string.bottomButtonTransition)
            )

            navigate(
                actionId = R.id.action_loginFragment_to_registrationFragment,
                extras = extras
            )
        }

        viewModel.authenticationResult.observe(viewLifecycleOwner) {
            if (it == AuthenticateUserUseCase.AuthenticationResult.Success) {
                val sharedPrefWrite = requireActivity().getPreferences(Context.MODE_PRIVATE)
                val editor = sharedPrefWrite.edit()
                viewModel.userRole.observe(viewLifecycleOwner) {
                    if (it != null) {
                        editor.putString(
                            SHARED_PREF_LOGIN,
                            it
                        )
                        editor.apply()
                    }
                    navigate(actionId = R.id.action_loginFragment_to_hackathonsListFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val SHARED_PREF_LOGIN = "login"
        private const val TAG = "LoginFragment"
    }
}