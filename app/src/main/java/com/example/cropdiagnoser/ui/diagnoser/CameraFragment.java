package com.example.cropdiagnoser.ui.diagnoser;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cropdiagnoser.MainActivity;
import com.example.cropdiagnoser.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CameraFragment extends AppCompatActivity {
    MediaMetadataRetriever retriever;
    public Handler handler;
    AssetFileDescriptor fileDescriptor;
    ImageView CameraFrame;
    ImageView shareRes;
    ImageView share;
    int VideoTime=0;
    ImageView CameraCapButton;
    public boolean hadCap=false;
    private View result;
    private PopupWindow popup_score;
    static CameraFragment instance;
    ImageView back;
    public static CameraFragment getInstance() {
        return instance;
    }

    private Bitmap Resize_bmp(Bitmap rootImg, int goalW, int goalH) {
        int rootW = rootImg.getWidth();
        int rootH = rootImg.getHeight();
        // graphics 包下的
        Matrix matrix = new Matrix();
        matrix.postScale(goalW * 1.0f / rootW, goalH * 1.0f / rootH);
        return Bitmap.createBitmap(rootImg, 0, 0, rootW, rootH, matrix, true);
    }
    private Runnable mTimeTask = new Runnable() {

        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public void run() {
            if(!hadCap)
            {
                // 得到每一秒时刻的bitmap比如第一秒,第二秒
                Bitmap bitmap = retriever.getFrameAtIndex(VideoTime);
                Display defaultDisplay = getWindowManager().getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                int x = point.x;
                int y = point.y;
                //bitmap=Resize_bmp(bitmap,x,y);
                CameraFrame.setImageBitmap(bitmap);
                VideoTime+=1;
            }
            handler.postDelayed(mTimeTask,1000/120);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        getSupportActionBar().hide();
//去掉标题栏注意这句一定要写在setContentView()方法的后面
        instance=this;
        CameraFrame=findViewById(R.id.CameraFrame);
        CameraCapButton=findViewById(R.id.cap);
        CameraCapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hadCap=true;
                MainActivity.getInstance().ResultShow();
            }
        });
        handler=new Handler();
        retriever = new MediaMetadataRetriever();
        try {
            fileDescriptor = getAssets().openFd("camera.MP4");
            retriever.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
            handler.postDelayed(mTimeTask,1000/120);
        }catch (Exception e)
        {

        }
    }


}
