package com.execmodule.trustchain_foc_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
    private String butt_text = null;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    private View.OnClickListener buttOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText et = (EditText) getActivity().findViewById(2);
            String tmp = et.getText().toString();


            Button butt = (Button) getActivity().findViewById(3);
            butt.setText(tmp);

            SharedPreferences mPrefs = getActivity().getSharedPreferences("my_db", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = mPrefs.edit();
            ed.putString("my_string", tmp);
            ed.commit();
        }
    };

//    @Override
//    public void onPause() {
//        super.onPause();
//
//        if(butt_text != null) {
//            SharedPreferences.Editor ed = mPrefs.edit();
//            ed.putString("my_string", butt_text);
//            ed.commit();
//        }
//    }


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout mylayout = new LinearLayout(container.getContext());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        EditText mytext = new EditText(container.getContext());
        mytext.setLayoutParams(lp);
        mytext.setId(2);
        mytext.setEms(10);
        mytext.setInputType(R.id.text);
        mytext.setHint("enter");
        mytext.setTextSize(14);

        mylayout.addView(mytext, 0);

        SharedPreferences mPrefs = getActivity().getSharedPreferences("my_db", Context.MODE_PRIVATE);
        Test tmpObj = new Test(mPrefs.getString("my_string", "hai"));

        Button mybutton = new Button(container.getContext());
        mybutton.setText(tmpObj.getString());
        mybutton.setId(3);
        mybutton.setOnClickListener(buttOnClickListener);
        mybutton.setLayoutParams(lp);
        mylayout.addView(mybutton, 1);

        View view = (View) mylayout;
        return view;
    }
}
