package ru.otus.demo.jobscheduler

import android.app.IntentService
import android.content.Intent
import android.util.Log

class DemoIntentService : IntentService(TAG_SCH) {
    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG_SCH, "Start of onHandleIntent ")
        try {
            Thread.sleep(10000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.d(TAG_SCH, "End of onHandleIntent ")
    }

    override fun onCreate() {
        Log.d(TAG_SCH, "onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG_SCH, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG_SCH, "onDestroy")
        super.onDestroy()
    }

    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    init {
        Log.d(TAG_SCH, "<Init>")
    }
}