package com.vasyancoder.feature_hackathon_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasyancoder.feature_calendar.domain.CalendarItem
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