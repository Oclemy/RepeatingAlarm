package com.tutorials.hp.repeatingalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Oclemy for ProgrammingWizards Channel and http://www.camposha.info.
 - Our MyReceiver class.
 - Derives from android.content.BroadcastReceiver.
 - We override onReceive() method and perform task to be done when alarm rings here.
 */

public class MyReceiver extends BroadcastReceiver {
    /*
   RING ALARM WHEN IN WHEN WE RECEIVE OUR BROADCAST
    */
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm Ringing...", Toast.LENGTH_SHORT).show();
    }
}
