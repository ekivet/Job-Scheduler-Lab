package com.example.erickivet.jobschedulers;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;

import java.util.Random;

/**
 * Created by erickivet on 9/4/16.
 */
public class JobServiceTwo extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        Random random = new Random();
        DataSingleton.getInstance().updateSecondString(String.valueOf(random.nextInt(100)));

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
