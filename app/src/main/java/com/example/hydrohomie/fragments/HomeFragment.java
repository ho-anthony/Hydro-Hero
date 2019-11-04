package com.example.hydrohomie.fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
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
        addButton = (Button) rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        reminderButton = (Button) rootView.findViewById(R.id.reminderButton);
        reminderButton.setOnClickListener(this);
        database = new DatabaseHelper(getContext());
        return rootView;
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addButton:
                addDrink(v);
                break;
            case R.id.reminderButton:
                //scheduleNotification();
                createNotificationChannel();
                startNotifications();
                break;
        }

    }

    public void addDrink(View v) {
        DateFormat day = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();
        boolean inserted = database.insertNewDrink(day.format(date), 8);
        if(inserted) {
            Toast.makeText(getContext(),"Inserted!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(),"FAILED!",Toast.LENGTH_SHORT).show();
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
