package com.example.rongcloudim;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.libutils.app.BaseApp;

import io.rong.imkit.RongIM;

public class RongCloudAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rongcloud);
        RongIM.init(BaseApp.get(), "p5tvi9dspl9d4");

    }
}
