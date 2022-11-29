package com.geektech.taskmanager.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.geektech.taskmanager.App
import com.geektech.taskmanager.R
import com.geektech.taskmanager.databinding.ItemTaskBinding
import com.geektech.taskmanager.data.model.Task

class TaskAdapter(
    private val taskList: ArrayList<Task> = arrayListOf(), val context: Context,
    val activity: FragmentActivity?, private val onClick: (Task) -> Unit
) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount() = taskList.size

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDescription.text = task.description

            itemView.setOnLongClickListener {
                createAlertDialog(task)
                return@setOnLongClickListener true
            }

            itemView.setOnClickListener{
                onClick(task)
            }

            changeColor(adapterPosition, binding)
        }
    }

    fun createAlertDialog(task: Task) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Delete task")
        alertDialogBuilder.setMessage("Are you sure, you want to delete the task?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            App.dataBase.taskDao().delete(task)
            activity?.recreate()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.show()
    }

//    fun addTask(task: Task) {
//        taskList.add(0, task)
//        notifyItemChanged(0)
//    }

    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(tasks: List<Task>) {
        this.taskList.clear()
        this.taskList.addAll(tasks)
        notifyDataSetChanged()
    }

    private fun changeColor(position:Int, binding: ItemTaskBinding){
        if (position % 2 != 0){
            binding.itemContainer.setBackgroundColor(Color.GRAY)
        }
    }
}
