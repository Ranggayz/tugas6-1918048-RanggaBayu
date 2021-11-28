package com.example.tugas6;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class MyWorker extends Worker{
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Result doWork() {
        for(int i=0; i<=100; i++){
            try {
                Thread.sleep(200);
                displayNotification("Peterpan Full Album", "Download in progress..." + i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayNotification(String tag, String msg){
        NotificationManager manager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel("testing",
                "test", NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),
                "testing")
                .setContentTitle(tag)
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        manager.notify(1, builder.build());
    }

}


