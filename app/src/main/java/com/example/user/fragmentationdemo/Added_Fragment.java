package com.example.user.fragmentationdemo;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 021 21.09.17.
 */

public class Added_Fragment extends Fragment {
    private final static String TAG = "FRAG";
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.added_fragment, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.i(TAG, "planetIndex=" + MainActivity.planetIndex);
            setText(MainActivity.amplifyRemark[MainActivity.planetIndex]);
        }
    }

    public void setText(String s) {
        tv = (TextView) getActivity().findViewById(R.id.TextView2);
        tv.setText(s);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // This seems necessary to manage things when there is the option of using the back button
        // to return to the previous state of the added fragment (because added to backstack in
        // MainActivity).

        MainActivity.fragAdded = false;
        Log.i(TAG, "onDestroyView() in Added_Fragment, fragAdded=" + MainActivity.fragAdded);
    }
}
