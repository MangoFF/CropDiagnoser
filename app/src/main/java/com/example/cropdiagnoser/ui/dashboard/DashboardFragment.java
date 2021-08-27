package com.example.cropdiagnoser.ui.dashboard;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cropdiagnoser.MainActivity;
import com.example.cropdiagnoser.R;
import com.example.cropdiagnoser.ui.diagnoser.CameraFragment;

public class DashboardFragment extends Fragment {

    static private DashboardViewModel dashboardViewModel;
    static MediaMetadataRetriever retriever;
    public Handler handler;
    AssetFileDescriptor fileDescriptor;
    ImageView CameraFrame;
    static int VideoTime=0;
    ImageView CameraCapButton;
    public boolean hadCap=false;
    private View result;
    private PopupWindow popup_score;
    static DashboardFragment instance;
    ImageView back;
    public static DashboardFragment getInstance() {
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

                //bitmap=Resize_bmp(bitmap,x,y);
                CameraFrame.setImageBitmap(bitmap);
                VideoTime+=1;
            }
            handler.postDelayed(mTimeTask,15);
        }
    };
    int x;int y;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.camera, container, false);
        instance=this;
        CameraFrame=root.findViewById(R.id.CameraFrame);
        CameraCapButton=root.findViewById(R.id.cap);
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
            fileDescriptor = MainActivity.getInstance().getAssets().openFd("camera.MP4");
            retriever.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
            handler.postDelayed(mTimeTask,1000/120);
        }catch (Exception e)
        {

        }
        Display defaultDisplay = MainActivity.getInstance().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
         x= point.x;
         y = point.y;
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

}