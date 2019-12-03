package com.example.hydrohomie.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.hydrohomie.R;
import com.example.hydrohomie.fragments.HistoryFragment;
import com.example.hydrohomie.fragments.HomeFragment;
import com.example.hydrohomie.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //sets default fragment to home fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_display,
                new HomeFragment()).commit();
    }

    //initialize tab bar at bottom
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    //switches created fragment based on user select
                    switch(menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_history:
                            selectedFragment = new HistoryFragment();
                            break;
                        case R.id.nav_settings:
                            selectedFragment = new SettingsFragment();
                            break;
                    }
                    //displays selected fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_display,
                            selectedFragment).commit();
                    return true;
                }
            };
}
