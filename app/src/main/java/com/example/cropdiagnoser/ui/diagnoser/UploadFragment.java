package com.example.cropdiagnoser.ui.diagnoser;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cropdiagnoser.MainActivity;
import com.example.cropdiagnoser.R;
import com.example.cropdiagnoser.ui.dashboard.DashboardFragment;

public class UploadFragment extends AppCompatActivity {
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
//去掉标题栏注意这句一定要写在setContentView()方法的后面
        setContentView(R.layout.result);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardFragment.getInstance().hadCap=false;
                onBackPressed();
            }
        });
    }
}

