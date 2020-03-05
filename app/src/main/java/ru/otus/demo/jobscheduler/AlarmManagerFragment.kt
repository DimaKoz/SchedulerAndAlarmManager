package ru.otus.demo.jobscheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_alarm_manager.*
import kotlinx.android.synthetic.main.fragment_scheduler.*


const val TAG_ALARM_M  = "AlarmManager"


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AlarmManagerFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_third).setOnClickListener {
            findNavController().navigate(R.id.action_AlarmManagerFragment_to_FirstFragment)
        }
        button_restart.setOnClickListener {
            restartApp(it.context)
        }
    }

    private fun restartApp(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        val requestCode = 123456 //any code
        val pendIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        val mgr = context.applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //analog- mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 200, pendIntent)
        mgr[AlarmManager.RTC, System.currentTimeMillis() + 250] = pendIntent

        //android.os.Process.killProcess(android.os.Process.myPid())

        // Values for exitProcess  EXIT_SUCCESS == 0 ,  EXIT_FAILURE == 1
        //exitProcess(0)

        activity?.finish()

    }

}
