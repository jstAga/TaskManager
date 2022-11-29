package com.geektech.taskmanager.ui.task

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.taskmanager.App
import com.geektech.taskmanager.R
import com.geektech.taskmanager.databinding.FragmentTaskBinding
import com.geektech.taskmanager.data.model.Task
import com.geektech.taskmanager.key.Key

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openTask()

        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text.toString().isNotEmpty()) {
                if (task !== null) {
                    updateTask()
                } else {
                    saveTask()
                }

            } else {
                binding.etTitle.error = getString(R.string.error_et_title)
            }

        }

    }

    private fun updateTask() {
        task?.title = binding.etTitle.text.toString()
        task?.description = binding.etDescription.text.toString()
        task?.let { App.dataBase.taskDao().update(it) }
        findNavController().navigateUp()
    }


    @SuppressLint("SetTextI18n")
    private fun openTask() {
        arguments?.let {
            val value = it.getSerializable(Key.KEY_FOR_BUNDLE_EDIT_TASK)
            if (value != null) {
                task = value as Task

                binding.etTitle.setText(task?.title.toString())
                binding.etDescription.setText(task?.description.toString())
                if (task != null) {
                    binding.btnSave.text = "Update"
                } else {
                    binding.btnSave.text = "Save"
                }
            }
        }
    }

    private fun saveTask() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString()
        )
        App.dataBase.taskDao().insert(data)
        findNavController().navigateUp()
    }
}