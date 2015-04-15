package com.ba.presentation.beginning_android.demo.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IgorOK on 22.03.2015.
 */
public class AlarmReceiver extends BroadcastReceiver {

    public static final long INTERVAL_8_SECONDS = 5 * 1000;

    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");

    @Override
    public void onReceive(Context context, Intent intent) {
        String date = formatDateTime(System.currentTimeMillis());
        Toast.makeText(context, date, Toast.LENGTH_LONG).show();
    }

    private static PendingIntent createPendingIntent(Context c) {
        Intent i = new Intent(c, AlarmReceiver.class);
        return PendingIntent.getBroadcast(c, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void stop(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi = createPendingIntent(context);
        am.cancel(pi);
        Toast.makeText(context, "Alarm Canceled ", Toast.LENGTH_SHORT).show();
    }

    public static void start(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pi = createPendingIntent(context);

        am.cancel(pi);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        /* Repeating on every 5 seconds interval */
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), INTERVAL_8_SECONDS, pi);
        Toast.makeText(context, "Alarm Set " + formatDateTime(calendar.getTimeInMillis()),
                Toast.LENGTH_SHORT).show();
    }

    public static String formatDateTime(long timestamp){
        return sDateFormat.format(new Date(timestamp));
    }

}