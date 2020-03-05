package ru.otus.demo.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_scheduler.*
import java.util.concurrent.TimeUnit


const val TAG_SCH  = "Scheduler"


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SchedulerFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scheduler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SchedulerFragment_to_FirstFragment)
        }
        button_schedule.setOnClickListener {
            scheduleJob(it.context)
        }
    }

    private fun scheduleJob(context: Context) {
        val jobService = ComponentName(context, DemoJobService::class.java)
        val jobBuilder = JobInfo.Builder(sJobId++, jobService)
        jobBuilder.setMinimumLatency(5000)
        jobBuilder.setOverrideDeadline(10000)
        jobBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
        jobBuilder.setRequiresDeviceIdle(false)
        jobBuilder.setRequiresCharging(false)
        jobBuilder.setPeriodic(10000)
        jobBuilder.setBackoffCriteria(
            TimeUnit.SECONDS.toMillis(10),
            JobInfo.BACKOFF_POLICY_LINEAR
        )
        Log.i(TAG_SCH, "scheduleJob: adding job to scheduler")
        val jobScheduler =
            context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobBuilder.build())
    }

    companion object  {

        var sJobId = 0
    }
}
