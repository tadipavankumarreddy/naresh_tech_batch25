package com.nareshtech.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast

// TODO 2: Implementation
class MyJobService: JobService(){
    override fun onStartJob(p0: JobParameters?): Boolean {
        // We have to define our own task to execute when the conditions are met.
        Toast.makeText(applicationContext,"Job is finished",Toast.LENGTH_SHORT).show()
        // This method returns false when the job is finished.
        return false
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Toast.makeText(applicationContext,"Job is Cancelled",Toast.LENGTH_SHORT).show()
        // This method returns false when the job is finished.
        return false
    }
}