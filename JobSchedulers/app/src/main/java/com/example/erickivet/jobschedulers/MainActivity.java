package com.example.erickivet.jobschedulers;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements DataSingleton.DataSingletonListener {

    TextView oldDataText1, newDataText1, oldDataText2, newDataText2, oldDataText3, newDataText3;

    DataSingleton dataSingleton;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInit();

        dataSingleton = DataSingleton.getInstance();
        dataSingleton.addDataChangeListener(this);

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        JobInfo firstJob = new JobInfo.Builder(1, new ComponentName(getPackageName(),
                JobServiceOne.class.getName())).setPeriodic(10000).build();

        JobInfo secondJob = new JobInfo.Builder(1, new ComponentName(getPackageName(),
                JobServiceTwo.class.getName())).setPeriodic(10000).build();

        JobInfo thirdJob = new JobInfo.Builder(1, new ComponentName(getPackageName(),
                JobServiceThree.class.getName())).setPeriodic(10000).build();

        jobScheduler.schedule(firstJob);
        jobScheduler.schedule(secondJob);
        jobScheduler.schedule(thirdJob);

    }

    private void textViewInit(){
        oldDataText1 = (TextView)findViewById(R.id.textview_old_task_1);
        oldDataText1.setText(DataSingleton.getInstance().getFirstString());
        oldDataText2 = (TextView)findViewById(R.id.textview_old_task_2);
        oldDataText2.setText(DataSingleton.getInstance().getSecondString());
        oldDataText3 = (TextView)findViewById(R.id.textview_old_task_3);
        oldDataText3.setText(DataSingleton.getInstance().getThirdString() + "");

        newDataText1 = (TextView) findViewById(R.id.textview_new_task_1);
        newDataText2 = (TextView) findViewById(R.id.textview_new_task_2);
        newDataText3 = (TextView) findViewById(R.id.textview_new_task_3);

    }

    @Override
    public void onFirstStringChanged(String oldString) {
        if (newDataText1.getText().length() > 0){
            oldDataText1.setText(oldString);
        }
        newDataText1.setText(dataSingleton.getFirstString());
    }

    @Override
    public void onSecondStringChanged(String oldString) {
        if(newDataText2.getText().length() > 0){
            oldDataText2.setText(oldString);
        }
        newDataText2.setText(dataSingleton.getSecondString());
    }

    @Override
    public void onThirdStringChanged(String oldString) {
        if (newDataText3.getText().length() > 0){
            oldDataText3.setText(oldString);
            try{
                oldDataText3.setBackgroundColor(Color.parseColor(oldString));
            }catch (IllegalArgumentException e){
                oldDataText3.setBackgroundColor(Color.RED);
                Log.e(TAG, "onThirdStringChaged: ", e);
            }
        }
        newDataText3.setText(dataSingleton.getThirdString());
        try{
            newDataText3.setBackgroundColor(Color.parseColor(dataSingleton.getThirdString()));
        }catch (IllegalArgumentException e){
            newDataText3.setBackgroundColor(Color.RED);
            Log.e(TAG, "onThirdStringChanged: ", e);
        }
    }
}
