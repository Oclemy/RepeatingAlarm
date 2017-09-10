package com.tutorials.hp.repeatingalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
- Our MainActivity class.
- Derives from AppCompatActivity which is a Base class for activities that use the support library action bar features.
- Methods: onCreate(),initializeViews(),initializeAlarmManager(),go().
- Inflated From activity_main.xml using the setContentView() method.
- The views we use are EditTexts and buttons.
- Reference them from our layout specification using findViewById().
- Initialize alarm manager.
- Start alarm using the setInExactRepeating().
 */
public class MainActivity extends AppCompatActivity {


    Button startBtn,cancelBtn;
    EditText timeTxt;
    AlarmManager alarmManager;
    PendingIntent pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		 initializeViews();
		 initializeAlarmManager();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go();
            }
        });

    }

    /*
    INITIALIZE VIEWS
     */
    private void initializeViews()
    {
        timeTxt= (EditText) findViewById(R.id.timeTxt);
        startBtn= (Button) findViewById(R.id.startBtn);
        cancelBtn= (Button) findViewById(R.id.cancelBtn);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alarmManager != null)
                {
                    alarmManager.cancel(pi);
                }
            }
        });
    }
	/*
	INITIALIZE AND START OUR ALARM
	*/
	private void initializeAlarmManager()
	{
		// INITIALIZE INTENT
		Intent intent=new Intent(this,MyReceiver.class);

        //PASS CONTEXT,YOUR PRIVATE REQUEST CODE,INTENT OBJECT AND FLAG
        pi= PendingIntent.getBroadcast(this,0,intent,0);

        //INITIALIZE ALARM MANAGER
        alarmManager= (AlarmManager) this.getSystemService(ALARM_SERVICE);
	}

    /*
    START OUR ALARM
     */
    private void go()
    {
        //GET TIME IN SECONDS
        int time=Integer.parseInt(timeTxt.getText().toString());


        //SET THE ALARM
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(time*1000),time*1000,pi);
            Toast.makeText(MainActivity.this, "Alarm set in "+time+" seconds", Toast.LENGTH_SHORT).show();

        }else
        {
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,System.currentTimeMillis()+(time*1000),time*1000,pi);
            Toast.makeText(MainActivity.this, "Yes Alarm set in "+time+" seconds", Toast.LENGTH_SHORT).show();

        }
    }

}
