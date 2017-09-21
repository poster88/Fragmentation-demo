package com.example.user.fragmentationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements List_Fragment.OnItemSelectedListener {

    private final static String TAG = "FRAG";
    public static boolean fragAdded = false;

    private Added_Fragment adder;
    android.app.FragmentManager fm;
    android.app.FragmentTransaction ft;

    // Static variable to track index of the current image file
    public static int planetIndex = 0;

    // Array of image files.  Files with these names must be copied into the project
    // assets subdirectory.  The assets directory will be copied to the device when
    // the app is installed. All of the following strings should more properly be
    // defined in strings.xml but they are hardwired in here for clarity.

    public static String planetImage[] = {
            "hst_mars_opp_9709a.jpg",
            "jupiter_gany.jpg",
            "saturn.jpg"};

    // Array of planet labels
    public static String planetLabel[] = {"Mars", "Jupiter", "Saturn"};

    // Array of amplifying remarks
    public static String amplifyRemark[] = {
            "Mars is called the red planet. It has a thin atmosphere and polar ice caps.",
            "Jupiter is the largest planet, and has very strong magnetic fields.",
            "Saturn has the lowest density of any planet, less than that of water."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "in onCreate, fragAdded =" + fragAdded);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Remove the amplifying remarks fragment if it exists to set a clean
        // stage if we come back.

        if (fragAdded && adder != null){
            fm = getFragmentManager();
            ft = fm.beginTransaction();
            ft.remove(adder);
            ft.commit();
            fragAdded = false;
            Log.i(TAG, "Removing fragment in MainActivity.onPause(). fragAdded=" + fragAdded);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "in onResume, fragAdded =" + fragAdded);
    }


    // This callback required because we are implementing List_Fragment.OnItemSelectedListener.
    // See the description of OnItemSelectedListener in List_Fragment.java. It will pass in the
    // id buttonNumber of the button pressed in List_Fragment.
    @Override
    public void onItemSelected(int buttonNumber) {
        Log.i("FRAG", "onItemSelected in MainActivity");
        // Get a reference to the Detail_Fragment (R.id.detailFragment is its ID) if it is in
        // the current view.

        Detail_Fragment detail_fragment = (Detail_Fragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        if (detail_fragment == null || !detail_fragment.isInLayout()){
            Log.i(TAG, "No instance of Detail_Fragment in this view, so launch new screen");
        } else {
            Log.i(TAG, "Instance of Detail_Fragment found in this view, so update the view");
        }

        // Determine which button was pressed and store its index in a static variable
        // for easy reference from other classes. We can do this because, even though
        // the buttons pressed are in the fragment List_Fragment, this callback passes
        // in the id of the button pressed from there.

        switch (buttonNumber) {
            // Choose Mars
            case R.id.button01:
                planetIndex = 0;
                break;
            // Choose Jupiter
            case R.id.button02:
                planetIndex = 1;
                break;
            // Choose Saturn
            case R.id.button03:
                planetIndex = 2;
                break;
            // Toggle adding and removing the third fragment using fragment transactions.
            case R.id.button04:
                if (detail_fragment == null) Log.i(TAG, "fragment is null");
                if (detail_fragment != null && detail_fragment.isInLayout()) {
                    Log.i(TAG, "fragAdded before transaction=" + fragAdded);
                    // If the third fragment has previously been added, remove it
                    if (fragAdded) {
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        ft.remove(adder);
                        // Example of replace
                        //ft.replace(R.id.newFragment, new List_Fragment());
                        ft.commit();
                        fragAdded = false;
                        // If the third fragment is not present, add it. It will go into the
                        // empty framelayout in res/layout-land/activity_main.xml.
                    } else {
                        adder = new Added_Fragment();
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        ft.add(R.id.newFragment, adder);
                        // Add transaction to backstack so the back button will remove the added
                        // fragment instead of taking you out of the app
                        ft.addToBackStack(null);
                        ft.commit();
                        // Bookkeeping to keep track of whether 3rd fragment is added
                        fragAdded = true;
                    }
                    Log.i(TAG, "fragAdded after transaction=" + fragAdded);
                }
                break;
        }

        // If the Detail_Fragment exists and is in the current layout, update its
        // text and image fields. If the 3rd fragment is in the layout, update its
        // text field.

        if (detail_fragment != null && detail_fragment.isInLayout()){
            detail_fragment.setText(planetLabel[planetIndex]);
            detail_fragment.setImage(planetImage[planetIndex]);
            if (adder != null && adder.isAdded()) adder.setText(amplifyRemark[planetIndex]);

            // If the Detail_Fragment is not in the current layout, we must be in portrait
            // mode.  Launch a new screen to display Detail_Fragment when a button is pressed.
        }else {
            Intent intent = new Intent(getApplicationContext(), Detail_Activity.class);
            startActivity(intent);
        }
    }



}