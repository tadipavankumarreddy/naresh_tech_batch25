package com.nareshtech.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // TODO 1: Create a JobScheduler
    lateinit var jobScheduler: JobScheduler
    lateinit var jobInfo: JobInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        // TODO 2: Create a JobService class to define the task and also cancel it if needed.
        // TODO 3: Define the conditions for your task to be executed through JobInfo
        jobInfo = JobInfo.Builder(45, ComponentName(packageName, MyJobService::class.java.name))
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .build()

        findViewById<Button>(R.id.button).setOnClickListener {
            jobScheduler.schedule(jobInfo)
        }
    }
}