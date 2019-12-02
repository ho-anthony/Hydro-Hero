package com.example.hydrohomie.fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.hydrohomie.R;
import com.example.hydrohomie.activities.PopUp;
import com.example.hydrohomie.database.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HomeFragment extends Fragment implements View.OnClickListener {
    Button addButton;
    Button reminderButton;
    DatabaseHelper database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container,false );

        //initialize add drink button functionality
        addButton = rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        //initialize reminder button functionality
        reminderButton =  rootView.findViewById(R.id.reminderButton);
        reminderButton.setOnClickListener(this);

        database = new DatabaseHelper(getContext());
        return rootView;

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addButton:
                //opens drink selection pop up window
                Intent myIntent = new Intent(HomeFragment.this.getActivity(), PopUp.class);
                HomeFragment.this.startActivity(myIntent);
                break;
            case R.id.reminderButton:
                //scheduleNotification();
                createNotificationChannel();
                startNotifications();
                break;
        }

    }


    private void startNotifications(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),"waterR")
                .setSmallIcon(R.drawable.water_notification_image)
                .setContentTitle("Drink up!")
                .setContentText("It is time to drink!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        notificationManager.notify(0,builder.build());

    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            //String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("waterR", name, NotificationManager.IMPORTANCE_DEFAULT);
            //channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
