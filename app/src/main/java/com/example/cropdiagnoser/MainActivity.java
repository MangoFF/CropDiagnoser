package com.example.cropdiagnoser;

import android.content.Intent;
import android.os.Bundle;

import com.example.cropdiagnoser.ui.diagnoser.CameraFragment;
import com.example.cropdiagnoser.ui.diagnoser.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    public static MainActivity instance;
    public CameraFragment cfragment;
    private final static String FRAGMENT_TAG = "SketchFragmentTag";
    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//去掉标题栏注意这句一定要写在setContentView()方法的后面
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        instance=this;
    }
    public void CameraShow()
    {
        Intent intent = new Intent(this,CameraFragment.class);
        startActivity(intent);
    }
    public void ResultShow()
    {
        Intent intent = new Intent(this, UploadFragment.class);
        startActivity(intent);
    }

}