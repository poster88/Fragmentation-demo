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
import android.widget.Button;

/**
 * Created by User on 021 21.09.17.
 */

public class List_Fragment extends Fragment{
    private OnItemSelectedListener listener;
    private static final String TAG = "FRAG";

    // The onCreate method of Fragment is called when the fragment is being
    // created. You cannot inflate fragment views here.  You must wait for
    // the onCreateView method to be called. You can initialize components of
    // the fragment here.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // The onCreateView method of Fragment is called when the fragment is
    // ready to draw its user interface for the first time.  This method must
    // return a View that is the root of the fragment layout, so that the
    // fragment view can be drawn (or it can return null if the fragment is
    // a worker fragment that does not have a layout). Only when this method has
    // been called can we safely inflate the view of the fragment.

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        Button button1 = (Button) view.findViewById(R.id.button01);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemSelected(R.id.button01);
            }
        });

        Button button2 = (Button) view.findViewById(R.id.button02);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemSelected(R.id.button02);
            }
        });

        Button button3 = (Button) view.findViewById(R.id.button03);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemSelected(R.id.button03);
            }
        });

        Button button4 = (Button) view.findViewById(R.id.button04);

        // Only adding the 3rd fragment in landscape mode, so hide the 4th button
        // if not in landscape.
        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE)
            button4.setVisibility(View.INVISIBLE);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemSelected(R.id.button04);
            }
        });
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

    // When a fragment has been associated with an activity, onAttach(activity) is called,
    // passing in the activity as its argument.


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "List_Fragment attached to " + getActivity().getComponentName().getClassName());

        // Instantiate the OnItemSelectedListener by casting the host activity (MainActivity,
        // in this case).  If the host MainActivity did not implement the interface OnItemSelectedListener,
        // this will throw a ClassCastException (since only if the interface was implemented can
        // MainActivity be cast to type OnItemSelectedListener).  If MainActivity did implement the
        // interface, the variable listener now holds a reference to MainActivity's implementation of
        // OnItemSelectedListener, so the present fragment can share events with the parent
        // activity MainActivity by calling methods defined in the OnItemSelectedListener interface.
        // For example, each time a button is clicked in the present fragment (see the onCreateView method
        // above), it fires the callback listener.onItemSelected(buttonID) in MainActivity.  By overriding the
        // onItemSelected(int buttonNumber) callback in MainActivity, we can then use the argument
        // buttonID that is passed by the callback to choose the image to display in fragment B (Detail_Fragment),
        // according to the button that was clicked in the present fragment.
        try {
            listener = (OnItemSelectedListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().getComponentName().getClassName()
                    + " must implement ListFragment.OnItemSelectedListener");
        }
    }

    /* When a fragment needs to share events with its host activity, a good approach
     * is to define a callback interface within the fragment and then require that
	 * the host activity implement it. For example, in the present application,
	 * fragment A (the present List_Fragment) displays buttons that choose which image
	 * is displayed in an ImageView in fragment B (Detail_Fragment).  Fragment A (the
	 * present List_Fragment) must tell its host activity (MainActivity) which item has
	 * been selected so that it can tell fragment B (Detail_Fragment) what to display.
	 * So we declare the OnItemSelectedListener interface below in fragment A (the
	 * present List_Fragment), and then require that the container activity (MainActivity
	 * in this case) implement this interface and override its methods (in this case,
	 * there is only one: onItemSelected(int buttonNumber)).
	 */

    // Define an interface that the container activity MainActivity will be  required to implement

    public interface OnItemSelectedListener {
        public void onItemSelected(int buttonNumber);
    }
}
