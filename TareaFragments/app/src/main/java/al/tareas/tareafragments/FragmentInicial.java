package al.tareas.tareafragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInicial#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInicial extends Fragment {

    OnFragmentInteractionListener mListener;

    public FragmentInicial() {
        // Required empty public constructor
    }

    public static FragmentInicial newInstance() {
        FragmentInicial fragment = new FragmentInicial();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mRadioButtonChoice = getArguments().getInt(CHOICE);
//        }
//    }

    interface OnFragmentInteractionListener{
        void onButtonChoice(String choice);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_inicial, container, false);

        final Button encA = rootView.findViewById(R.id.btnEncuestaA);
        final Button encB = rootView.findViewById(R.id.btnEncuestaB);

        encA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonChoice("a");
            }
        });

        encB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonChoice("b");
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof  OnFragmentInteractionListener){
            mListener =( OnFragmentInteractionListener) context;
        }
        else {
            throw new ClassCastException(context.toString() + getResources().getString(R.string.exception_message));
        }
    }
}
