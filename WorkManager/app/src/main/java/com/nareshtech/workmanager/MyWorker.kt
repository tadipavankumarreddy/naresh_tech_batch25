package com.nareshtech.workmanager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

// TODO 1: Define a worker to create your own task.
class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    // All the work that has to happen can be defined here.
    override fun doWork(): Result {
        Log.v("MAIN", "Worker is running")
        return Result.success()
    }
}