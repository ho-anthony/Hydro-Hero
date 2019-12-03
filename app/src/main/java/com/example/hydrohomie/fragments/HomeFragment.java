package com.example.hydrohomie.fragments;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import com.example.hydrohomie.R;
import com.example.hydrohomie.activities.PopUp;
import com.example.hydrohomie.activities.TimerPopUp;
import com.example.hydrohomie.database.DatabaseHelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import me.itangqi.waveloadingview.WaveLoadingView;
import static android.content.Context.MODE_PRIVATE;
import static com.example.hydrohomie.activities.ProfileActivity.AGE;
import static com.example.hydrohomie.activities.ProfileActivity.NAME;
import static com.example.hydrohomie.activities.ProfileActivity.SHARED_PREFS;
import static com.example.hydrohomie.activities.ProfileActivity.WEIGHT;


public class HomeFragment extends Fragment implements View.OnClickListener {
    //initialize variables
    Button addButton;
    Button reminderButton;
    DatabaseHelper database;
    Timer timer;
    boolean timerExists = false;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String WEIGHT = "weight";
    public String savedName;
    public Integer savedAge;
    public Integer savedWeight;
    public Integer recommendation;
    TextView progressText;
    TextView nameText;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container,false );
        //load data from shared preferences file
        loadData();
        nameText = (TextView) rootView.findViewById(R.id.nameText);
        nameText.setText(savedName);
        if(savedAge < 30 && savedAge > 1){
            recommendation = ((savedWeight / 2) * 40) / 28;
        }else if (savedAge >= 30 && savedAge <= 55){
            recommendation=((savedWeight / 2) * 35) / 28;
        }else if(savedAge > 55){
            recommendation=((savedWeight / 2) * 30) / 28;
        }else{
            recommendation=((2 / 2) * 40) / 28;
        }
        addButton = (Button) rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        reminderButton = (Button) rootView.findViewById(R.id.reminderButton);
        reminderButton.setOnClickListener(this);
        database = new DatabaseHelper(getContext());
        DateFormat day = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();
        boolean inserted = database.insertNewDrink(day.format(date), 0,
                date.getTime()/1000);
        int currentLoggedAmount = database.getLoggedValue();
        progressText = (TextView) rootView.findViewById(R.id.progressText);
        progressText.setText(currentLoggedAmount + " / " + recommendation + " OZ");
        WaveLoadingView waveLoadingView = (WaveLoadingView)
                rootView.findViewById(R.id.waveLoadingView);
        int percentage = currentLoggedAmount * 100 / recommendation;
        waveLoadingView.setProgressValue(percentage);
        return rootView;
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addButton:
                //opens drink selection pop up window
                Intent myIntent = new Intent(HomeFragment.this.getActivity(), PopUp.class);
                HomeFragment.this.startActivityForResult(myIntent, 2);
                break;
            case R.id.reminderButton:
                Intent newIntent = new Intent(HomeFragment.this.getActivity(), TimerPopUp.class);
                HomeFragment.this.startActivityForResult(newIntent,1);
                break;
        }
    }

    //gets results from pop up windows
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                if(result.equals("Cancel")) {
                    if(timerExists) {
                        timer.cancel();
                        timer.purge();
                        timerExists = false;
                    }
                } else {
                    int timeInMilli = Integer.parseInt(result) * 60000;
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            createNotificationChannel();
                            startNotifications();
                        }
                    };
                    if (timerExists) {
                        timer.cancel();
                        timer.purge();
                    }
                    timerExists = true;
                    timer = new Timer();
                    timer.schedule(task, timeInMilli, timeInMilli);
                }
            }
        } else if(requestCode == 2) {
            int currentLoggedAmount = database.getLoggedValue();
            progressText = (TextView) rootView.findViewById(R.id.progressText);
            progressText.setText(currentLoggedAmount + " / " + recommendation + " OZ");
            WaveLoadingView waveLoadingView = (WaveLoadingView)
                    rootView.findViewById(R.id.waveLoadingView);
            int percentage = currentLoggedAmount * 100 / recommendation;
            waveLoadingView.setProgressValue(percentage);
        }
    }

    //starts notifications
    private void startNotifications(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),
                "waterR")
                .setSmallIcon(R.drawable.water_notification_image)
                .setContentTitle("Drink up!")
                .setContentText("It is time to drink!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        notificationManager.notify(0 , builder.build());
    }


    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("waterR", name, NotificationManager.IMPORTANCE_DEFAULT);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    //load data
    public void loadData(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        savedName = sharedPreferences.getString(NAME, "");
        savedAge = sharedPreferences.getInt(AGE, 18);
        savedWeight = sharedPreferences.getInt(WEIGHT, 90);
    }
}
