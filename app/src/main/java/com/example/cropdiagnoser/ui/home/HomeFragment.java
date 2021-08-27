package com.example.cropdiagnoser.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cropdiagnoser.MainActivity;
import com.example.cropdiagnoser.R;
import com.example.cropdiagnoser.ui.diagnoser.CameraFragment;

public class HomeFragment extends Fragment {
    private final static String FRAGMENT_TAG = "SketchFragmentTag";
    private HomeViewModel homeViewModel;
    private ImageView camera;
    private  Fragment camera_fragment;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }
}