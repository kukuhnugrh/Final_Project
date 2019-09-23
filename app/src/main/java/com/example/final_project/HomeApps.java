package com.example.final_project;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.net.ConnectivityManagerCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.final_project.service.MyJobService;

import java.util.Objects;

@SuppressLint("Registered")
public class HomeApps extends AppCompatActivity {
    Button button;
    Button firstFragmen;
    boolean isReciverRegistered = false;
    boolean wifiConnected = false;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        //keAbout();
        logOut();
        //fragmenDua();
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        Bundle b = getIntent().getExtras();
        String s;
        s = b.getString("flag");
        Toast.makeText(this, "HALO "+s, Toast.LENGTH_SHORT).show();
        createNotificationChannel();
    }


//    protected void keAbout() {
//        button = findViewById(R.id.about);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent about = new Intent(HomeApps.this, Tentang.class);
//                startActivity(about);
//            }
//
//        });
//    }
    protected void logOut(){
        button = findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent main = new Intent(HomeApps.this, MainActivity.class);
                startActivity(main);
            }
        });
    }

    public void notif(String status){
        NotificationCompat.Builder notifBuilder =
                new NotificationCompat.Builder(this, "1")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("WiFi")
                        .setContentText("WiFi is "+ status)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notifIntent = new Intent(this, HomeApps.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notifIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notifBuilder.setContentIntent(contentIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, notifBuilder.build());

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notification_channel";
            String description = "desciption of notification channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo  = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();

            if(networkInfo !=null){
                if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                    System.out.println("status: " + networkInfo.isConnected());
                    Toast.makeText(getApplicationContext(),"wifi off",Toast.LENGTH_SHORT).show();
                    notif("Wifi Off");
                }
                else{
                    System.out.println("status: " + networkInfo.isConnected());
                    Toast.makeText(getApplicationContext(),"wifi on",Toast.LENGTH_SHORT).show();
                    notif("Wifi On");
                }
            }

        }
    };

    protected void onResume(){
        super.onResume();
        if(!isReciverRegistered){
            isReciverRegistered =  true;
            wifiConnected = true;
            registerReceiver(receiver, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        }
    }

    protected void onPause(){
        super.onPause();
        if(isReciverRegistered){

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob(View v) {
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelJob(View v) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }
}
