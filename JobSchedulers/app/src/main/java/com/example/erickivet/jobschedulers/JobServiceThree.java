package com.example.erickivet.jobschedulers;

import android.app.job.JobParameters;
import android.app.job.JobService;

import java.util.Random;

/**
 * Created by erickivet on 9/4/16.
 */
public class JobServiceThree extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        Random random = new Random();
        String red = Integer.toHexString(random.nextInt(256));
        String blue = Integer.toHexString(random.nextInt(256));
        String green = Integer.toHexString(random.nextInt(256));
        String color = String.format("#%s%s%s", red, blue, green);
        DataSingleton.getInstance().updateThirdString(color);

        jobFinished(params, false);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
