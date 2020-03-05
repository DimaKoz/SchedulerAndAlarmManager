package ru.otus.demo.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log

class DemoJobService : JobService() {

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.i(TAG_SCH, "onStopJob: stopping job with id: " + params?.jobId)
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.i(TAG_SCH, "onStartJob: starting job with id: " + params?.jobId)
        val startIntentService = Intent(this, DemoIntentService::class.java)
        startService(startIntentService)
        return true
    }

}