package com.vasyancoder.feature_welcome.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.vasyancoder.feature_welcome.presentation.databinding.FragmentGreetingBinding
import com.vasyancoder.navigation.navigate

class GreetingFragment : Fragment() {
    private var _binding: FragmentGreetingBinding? = null
    private val binding: FragmentGreetingBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textPromptLogIn.setOnClickListener {
            val extras: Navigator.Extras = FragmentNavigatorExtras(
                binding.buttonCreateAccount to
                        getString(com.vasyancoder.core.R.string.bottomButtonTransition)
            )

            navigate(
                actionId = R.id.action_greetingFragment_to_loginFragment,
                extras = extras
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}