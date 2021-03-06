package com.example.erickivet.jobschedulers;

import android.app.job.JobParameters;
import android.app.job.JobService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by erickivet on 9/4/16.
 */
public class JobServiceOne extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        DataSingleton.getInstance().updateFirstString(timeFormat.format(cal.getTime()));

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
