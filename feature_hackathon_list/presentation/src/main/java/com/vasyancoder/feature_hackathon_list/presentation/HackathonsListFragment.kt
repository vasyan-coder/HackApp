package com.vasyancoder.feature_hackathon_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasyancoder.core.Utils
import com.vasyancoder.feature_calendar.domain.CalendarItem
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon
import com.vasyancoder.feature_hackathon_list.presentation.databinding.FragmentHackathonsListBinding
import com.vasyancoder.feature_hackathon_list.presentation.hackathon_list.HackathonsAdapter
import com.vasyancoder.feature_hackathon_list.presentation.tag_list.TagAdapter

class HackathonsListFragment : Fragment() {

    private var _binding: FragmentHackathonsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HackathonsListViewModel by viewModels()

    private lateinit var hackathonsAdapter: HackathonsAdapter
    private lateinit var tagAdapter: TagAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHackathonsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Utils.userRole == "admin") {
            binding.floatingActionButtonAdd.visibility = View.VISIBLE
            binding.floatingActionButtonAdd.setOnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                val layout = LinearLayout(requireContext())

                layout.orientation = LinearLayout.VERTICAL

                val hackathonEt = EditText(requireContext())
                hackathonEt.hint = "Hackathon name"
                layout.addView(hackathonEt)

                val organizationEt = EditText(requireContext())
                organizationEt.hint = "Organization"
                layout.addView(organizationEt)

                val datesEt = EditText(requireContext())
                datesEt.hint = "Dates"
                layout.addView(datesEt)

                val statusEt = EditText(requireContext())
                statusEt.hint = "Status"
                layout.addView(statusEt)

                builder.setView(layout)

                builder.setPositiveButton("Submit") { _, _ ->
                    val hackathonName = hackathonEt.text.toString()
                    val organization = organizationEt.text.toString()
                    val status = statusEt.text.toString()
                    val dates = datesEt.text.toString()
                    viewModel.addHackathon(
                        Hackathon(
                            name = hackathonName,
                            organization = organization,
                            dates = dates,
                            status = status,
                        )
                    )
                }
                builder.setNegativeButton("Cancel", null)

                val dialog = builder.create()
                dialog.show()
            }
        } else {
            binding.floatingActionButtonAdd.visibility = View.INVISIBLE
        }

        viewModel.updateHackathons("")

        setUpHackathonsRecyclerView()
        setUpTagRecyclerView()

        tagAdapter.submitList(viewModel.tagsList)

        viewModel.hackathonList.observe(viewLifecycleOwner) {
            hackathonsAdapter.submitList(it)
        }
    }

    private fun setUpHackathonsRecyclerView() {
        with(binding.recyclerHackathons) {
            hackathonsAdapter = HackathonsAdapter()
            hackathonsAdapter.onHackathonClickListener = {
                viewModel.addCalendarItem(
                    CalendarItem(
                        name = it.name,
                        organization = it.organization,
                        dates = it.dates,
                        status = it.status
                    )
                )
            }
            adapter = hackathonsAdapter
        }
    }

    private fun setUpTagRecyclerView() {
        with(binding.recyclerTags) {
            tagAdapter = TagAdapter()
            adapter = tagAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}