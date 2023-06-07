package com.vasyancoder.feature_registration.presentation

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.vasyancoder.feature_registration.domain.RegistrationUserUseCase
import com.vasyancoder.feature_registration.presentation.databinding.FragmentRegistrationBinding
import com.vasyancoder.navigation.navigate

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegistrationViewModel by viewModels()

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
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegistration.setOnClickListener {
            viewModel.registerUser(
                login = binding.textInputLoginEditText.text.toString(),
                password = binding.textInputPasswordEditText.text.toString(),
                confirmPassword = binding.textInputConfirmPasswordEditText.text.toString(),
                email = binding.textInputEmailEditText.text.toString()
            )
        }


//        viewModel.validation.observe(viewLifecycleOwner) {
//            binding.textInputPasswordLayout.error = it
//            binding.textInputLoginEditText.doOnTextChanged { text, start, before, count ->
//                binding.textInputLoginLayout.error = ""
//            }
//        }

        viewModel.registrationResult.observe(viewLifecycleOwner) {
            if (it == RegistrationUserUseCase.RegistrationResult.Success) {
                val extras: Navigator.Extras = FragmentNavigatorExtras(
                    binding.buttonRegistration to
                            getString(com.vasyancoder.core.R.string.bottomButtonTransition)
                )

                navigate(
                    actionId = R.id.action_registrationFragment_to_loginFragment,
                    extras = extras
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}