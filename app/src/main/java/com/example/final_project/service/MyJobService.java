package com.example.final_project.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        // Write your code here
        Log.d(TAG, "Job started");
        doBackgroundWork(params);

        return true;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0 ; i<5400 ; i++){
                    //if(i%3 == 0){
                        Handler mainHandler = new Handler(getMainLooper());
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // Do your stuff here related to UI, e.g. show toast
                                Toast.makeText(getApplicationContext(), "I'm a toast!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    //}
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }

}
