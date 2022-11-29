package com.geektech.taskmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geektech.taskmanager.App
import com.geektech.taskmanager.R
import com.geektech.taskmanager.data.model.Task
import com.geektech.taskmanager.databinding.FragmentHomeBinding
import com.geektech.taskmanager.key.Key
import com.geektech.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var taskAdapter:  TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter(context = requireContext(), activity = activity,
            onClick = this::onTaskClick
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = App.dataBase.taskDao().getAllTask()

        taskAdapter.addTasks(data)

        binding.rvTask.adapter = taskAdapter
        binding.btnFab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun onTaskClick(task : Task){
        findNavController().navigate(R.id.taskFragment, bundleOf(Key.KEY_FOR_BUNDLE_EDIT_TASK to task) )
    }
}