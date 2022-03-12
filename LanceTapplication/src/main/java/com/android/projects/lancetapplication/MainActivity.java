package com.android.projects.lancetapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.android.projects.myjarlibrary.Tools;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//     com.test.utils.Tools.evilCode();
        Tools.evilCode();
    }
}