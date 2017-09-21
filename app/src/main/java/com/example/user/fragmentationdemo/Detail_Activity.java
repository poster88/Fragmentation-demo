package com.example.user.fragmentationdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by User on 021 21.09.17.
 */

public class Detail_Activity extends AppCompatActivity{

    private final static  String TAG = "FRAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Detail_activity created");

        // Check if Activity has been switched to landscape mode.
        // If yes, finish and return to the start Activity

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }
        setContentView(R.layout.activity_detail);
        TextView view = (TextView) findViewById(R.id.planetLabel);
        view.setText(MainActivity.planetLabel[MainActivity.planetIndex]);
    }
}
