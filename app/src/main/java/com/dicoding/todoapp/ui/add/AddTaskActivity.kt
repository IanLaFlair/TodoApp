package com.dicoding.todoapp.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.databinding.ActivityAddTaskBinding
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class AddTaskActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener {
    private var dueDateMillis: Long = System.currentTimeMillis()
    private lateinit var addTaskViewModel: AddTaskViewModel
    private lateinit var activityAddTaskBinding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddTaskBinding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(activityAddTaskBinding.root)

        supportActionBar?.title = getString(R.string.add_task)
        addTaskViewModel = obtainViewModel(this@AddTaskActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
               //TODO 12 : Create AddTaskViewModel and insert new task to database
                val title = activityAddTaskBinding.addEdTitle.text.toString()
                val description = activityAddTaskBinding.addEdDescription.text.toString()
                insertTask(title,description,dueDateMillis)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun insertTask(title: String, description: String, duedatemills: Long){
        val task: Task?
        task = Task(0,title,description,dueDateMillis,false)
        addTaskViewModel.insert(task)
        Toast.makeText(applicationContext, "Success Add New Task", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun showDatePicker(view: View) {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(supportFragmentManager, "datePicker")
    }

    private fun obtainViewModel(activity: AppCompatActivity): AddTaskViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(AddTaskViewModel::class.java)
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        findViewById<TextView>(R.id.add_tv_due_date).text = dateFormat.format(calendar.time)

        dueDateMillis = calendar.timeInMillis
    }
}