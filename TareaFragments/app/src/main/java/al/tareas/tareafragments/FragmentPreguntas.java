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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPreguntas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPreguntas extends Fragment {

    OnFragmentInteractionListener mListener;
    private static final String CHOICE = "choice";
    private static final String QUESTION = "question";

    private static final int TRUE = 0;
    private static final int FALSE = 1 ;
    private static final int NONE = 2;

    public int mRadioButtonChoice = NONE;
    public int numQuestion = 1;

    public FragmentPreguntas() {
        // Required empty public constructor
    }

    public static FragmentPreguntas newInstance(int choice, int question) {
        FragmentPreguntas fragment = new FragmentPreguntas();
        Bundle args = new Bundle();
        args.putInt(CHOICE, choice);
        args.putInt(QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    interface OnFragmentInteractionListener{
        void onRadioButtonChoice(int choice);
        void setQuestion(int num);
        String getQuestion();
        void checkQuestion();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            numQuestion = getArguments().getInt(QUESTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_preguntas, container, false);

        final TextView pregunta = rootView.findViewById(R.id.text_pregunta);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        final Button prevBtn = rootView.findViewById(R.id.btnPrev);
        final Button nextBtn = rootView.findViewById(R.id.btnNext);
        final Button checkBtn = rootView.findViewById(R.id.btnCheckAnswer);
        final TextView pages = rootView.findViewById(R.id.paginado);

        pregunta.setText(mListener.getQuestion());
        pages.setText("[" + numQuestion + "/5]");

        if(getArguments().containsKey(CHOICE)){
            mRadioButtonChoice = getArguments().getInt(CHOICE);

            if(mRadioButtonChoice != NONE){
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                switch (index){
                    case TRUE:
                        mRadioButtonChoice = TRUE;
                        mListener.onRadioButtonChoice(TRUE);
                        checkBtn.setVisibility(View.VISIBLE);
                        break;
                    case FALSE:
                        mRadioButtonChoice = FALSE;
                        mListener.onRadioButtonChoice(FALSE);
                        checkBtn.setVisibility(View.VISIBLE);
                        break;
                    default:
                        mRadioButtonChoice = NONE;
                        mListener.onRadioButtonChoice(NONE);
                        checkBtn.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                mListener.setQuestion(--numQuestion);
                pregunta.setText(mListener.getQuestion());
                pages.setText("[" + numQuestion + "/5]");
                if (numQuestion == 1) {
                    prevBtn.setEnabled(false);
                }
                nextBtn.setEnabled(true);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                mListener.setQuestion(++numQuestion);
                pregunta.setText(mListener.getQuestion());
                pages.setText("[" + numQuestion + "/5]");
                if (numQuestion == 5) {
                    nextBtn.setEnabled(false);
                }
                prevBtn.setEnabled(true);
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.checkQuestion();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener =(OnFragmentInteractionListener) context;
        }
        else {
            throw new ClassCastException(context.toString() + getResources().getString(R.string.exception_message));
        }
    }
}
