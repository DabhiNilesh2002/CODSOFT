package com.example.custome_notification_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String channelId = "MY Channel";
    private static final int notificationId = 100;
    private static final int REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(),
                R.drawable.saleofferupto60, null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager NM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;

        Intent iNotify = new Intent(getApplicationContext(), ShowMsgActivity.class);

        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent PI = PendingIntent.getActivity(this, REQ_CODE,
                iNotify, PendingIntent.FLAG_IMMUTABLE);

        //1st Way Notification Show Image Style
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(((BitmapDrawable) (ResourcesCompat.getDrawable(getResources(),
                        R.drawable.saleofferupto60, null))).getBitmap())
                .bigLargeIcon(largeIcon)
                .setBigContentTitle("Image Sent by Nilesh")
                .setSummaryText("Image Message");

        //2nd Way Notification Show Inbox Style
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .addLine("Hiii")
                .addLine("Hello")
                .addLine("How Are You?")
                .addLine("I am fine...!")
                .addLine("What's going on?")
                .addLine("That's Nothing...")
                .addLine("What about you?")
                .addLine("Same as ever...")
                .setBigContentTitle("Dabhi Nilesh")
                .setSummaryText("Message Form Nilesh");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.clth)
                    .setContentTitle("Hello Dabhi Nilesh")
                    .setContentText("This is Big Sale Offer Hurry Up to buy a few hour")
                    .setChannelId(channelId)
                    .setStyle(inboxStyle)
                    .setContentIntent(PI)
                    .build();

            NM.createNotificationChannel(new NotificationChannel(channelId, "New Channel",
                    NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.clth)
                    .setContentTitle("Hello Title")
                    .setContentText("This is Content Text")
                    .setStyle(bigPictureStyle)
                    .setContentIntent(PI)
                    .build();
        }

        NM.notify(notificationId, notification);

    }
}
