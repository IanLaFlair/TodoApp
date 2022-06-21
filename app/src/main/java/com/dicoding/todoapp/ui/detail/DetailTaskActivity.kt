package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.databinding.ActivityAddTaskBinding
import com.dicoding.todoapp.databinding.ActivityTaskDetailBinding
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel
    private lateinit var binding: ActivityTaskDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vmFactory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, vmFactory).get(DetailTaskViewModel::class.java)
        val id = intent.getIntExtra(TASK_ID,0)
        detailTaskViewModel.setTaskId(id)
        detailTaskViewModel.task.observe(this){
            if (it != null){
                binding.detailEdTitle.setText(it.title)
                binding.detailEdDueDate.setText(DateConverter.convertMillisToString(it.dueDateMillis))
                binding.detailEdDescription.setText(it.description)
            }
        }
        binding.btnDeleteTask.setOnClickListener {
            detailTaskViewModel.deleteTask()
            finish()
        }
        //TODO 11 : Show detail task and implement delete action

    }
}