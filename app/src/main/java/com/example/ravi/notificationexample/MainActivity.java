package com.example.ravi.notificationexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnSimpleNot, btnInboxStyleNot, btnBigTextStyleNot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimpleNot = (Button) findViewById(R.id.button_simple);
        btnInboxStyleNot = (Button) findViewById(R.id.button_inbox_style);
        btnBigTextStyleNot = (Button) findViewById(R.id.button_bigtext_style);

        btnSimpleNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleNotificationFunc();
            }
        });

        btnInboxStyleNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inboxStyleNotificationFunc();
            }
        });
    }

    private void inboxStyleNotificationFunc() {
        //set intents and pedning intents to call activity on click of "show activity" action button of notification
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent piResult = PendingIntent.getActivity(this, (int) Calendar.getInstance().getTimeInMillis(), resultIntent,0);


        //assign inbox style notification
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Inbox Notification");
        inboxStyle.addLine("Message 1.");
        inboxStyle.addLine("Message 2.");
        inboxStyle.addLine("Message 3.");
        inboxStyle.addLine("Message 4.");
        inboxStyle.addLine("Message 5.");
        inboxStyle.setSummaryText("+2 more");

        //build Notification
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Inbox Style Notification")
                .setContentText("This is a test of Inbox style notification")
                .setStyle(inboxStyle)
                .addAction(R.mipmap.ic_launcher,"Show activity",piResult);

        //get instance of the notification manager service
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //to post the notification to the notification bar
        notificationManager.notify(0,mBuilder.build());
    }

    private void simpleNotificationFunc() {
        //build Notification
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Simple Notification")
                .setContentText("This is a test of simple notification.");

        //get an instance of the NotificationManager service
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //post notification to the notification bar
        notificationManager.notify(0,mBuilder.build());

    }
}
