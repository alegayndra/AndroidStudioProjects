package com.example.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    OnFragmentInteractionListener mListener;

    private static final int YES = 0;
    private static final int NO = 1 ;
    private static final int NONE = 2;

    public int mRadioButtonChoice = NONE;

    interface OnFragmentInteractionListener {
        void onRadioButtonChoice(int choice);
    }

    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment newInstance() {
        return new SimpleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_simple, container, false);

        final RadioGroup radioGroup= rootView.findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                TextView textView = rootView.findViewById(R.id.fragment_header);

                switch (index){


                    case YES:
                        textView.setText(R.string.yes_message);
                        mRadioButtonChoice = YES;
                        mListener.onRadioButtonChoice(YES);
                        break;

                    case NO:
                        textView.setText(R.string.no_message);
                        mRadioButtonChoice = NO;
                        mListener.onRadioButtonChoice(NO);
                        break;

                    default:
                        mRadioButtonChoice = NONE;
                        mListener.onRadioButtonChoice(NONE);
                        break;


                }


            }
        });



        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof  OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        else {
            throw new ClassCastException(context.toString());
        }
    }

}
