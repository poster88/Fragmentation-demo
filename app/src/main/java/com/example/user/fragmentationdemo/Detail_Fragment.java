package com.example.user.fragmentationdemo;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User on 021 21.09.17.
 */

public class Detail_Fragment extends Fragment{
    private ImageView imageView;
    private final static  String TAG = "FRAG";

    // The onCreate method of Fragment is called when the fragment is being
    // created. You cannot inflate fragment views here.  You must wait for
    // the onCreateView method to be called. You can initialize components of
    // the fragment here.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // onCreateView() must be implemented by fragment to provide a layout. It
    // will be called when it is time for the fragment to draw its layout.  This
    // method must return a View that is the root of the fragment's layout. The
    // LayoutInflater inflates the view from the XML layout resource. The container
    // argument passed to OnCreateView is the ViewGroup parent into which the fragment
    // view will be inserted. The savedInstanceState bundle passed as an argument
    // contains data about the previous instance of the fragment, if it is being
    // resumed.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }

    // The onPause method of Fragment is called when the user is no longer interacting
    // with the fragment. This can occur because its activity is being paused, or
    // because a fragment operation is modifying it in the activity. Any changes that
    // should be persisted beyond the current user session should be committed here.
    @Override
    public void onPause() {
        super.onPause();
    }

    // onActivityCreated is called when the activity hosting the fragment has returned
    // from its own Activity.onCreate method. The  view can now be accessed
    // by findViewById(), for example.
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, getActivity().getComponentName().getClassName()+" has started");
        setImage(MainActivity.planetImage[MainActivity.planetIndex]);
        setText(MainActivity.planetLabel[MainActivity.planetIndex]);

    }

    // Load an image from file assetFile in the assets directory and display it.  The
    // contents of the assets directory are exported when the app is installed.
    private void setImage(String assetFile) {
        try {
            // Get handle to the viewer
            imageView = (ImageView) getView().findViewById(R.id.image);
            // Get an input stream from image file in the assets directory
            InputStream is = getActivity().getAssets().open(assetFile);
            // Create a Drawable from the image stream
            Drawable drawable = Drawable.createFromStream(is, null);
            // Add the Drawable to the ImageView
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            return;
        }
    }

    // Set the planet label
    private void setText(String s) {
        TextView textView = (TextView) getView().findViewById(R.id.planetLabel);
        textView.setText(s);
    }

    // When a fragment has been associated with an activity, onAttach(activity) is called,
    // passing in the activity as its argument. It is associated with its activity, but
    // may not be fully initialized.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "Detail_Fragment attached to " + getActivity().getComponentName().getClassName());
    }




}
