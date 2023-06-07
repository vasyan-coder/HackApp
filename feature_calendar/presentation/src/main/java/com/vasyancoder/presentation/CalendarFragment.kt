package com.vasyancoder.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.vasyancoder.navigation.navigate
import com.vasyancoder.presentation.calendar_list.CalendarAdapter
import com.vasyancoder.presentation.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalendarViewModel by viewModels()

    private lateinit var calendarAdapter: CalendarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.calendarList.observe(viewLifecycleOwner) {
            calendarAdapter.submitList(it)
        }
        binding.buttonLogOut.setOnClickListener {
            // write
            val sharedPrefWrite = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPrefWrite.edit()
            editor.putString(
                SHARED_PREF_LOGIN,
                ""
            )
            editor.apply()

            navigate(actionId = R.id.action_calendarFragment_to_greetingFragment)
        }
    }

    private fun setupRecyclerView() {
        with(binding.recyclerCalendar) {
            calendarAdapter = CalendarAdapter()
            adapter = calendarAdapter
        }
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = calendarAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteCalendarItem(item.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerCalendar)
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