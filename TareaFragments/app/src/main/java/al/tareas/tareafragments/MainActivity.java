package al.tareas.tareafragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentInicial.OnFragmentInteractionListener, FragmentPreguntas.OnFragmentInteractionListener {

    // Choices
    private int mRadioButtonChoice = 2;
    private int numQuestion = 1;
    private boolean isFragmentDisplayed = false;
    private String questionary = "";
    static final String STATE_FRAGMENT = "state_of_fragment";
    static final String SELECTED_QUESTIONARY = "selected_questionary";
    static final String CURRENT_QUESTION  = "selected_questionary";

    private QuestionLoader questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questions = new QuestionLoader();

        if (savedInstanceState != null){
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);

            if (isFragmentDisplayed){
                questionary = savedInstanceState.getString(SELECTED_QUESTIONARY);
                numQuestion = savedInstanceState.getInt(CURRENT_QUESTION);
                mostrarFragemtPreguntas();
            } else {
                mostrarFragmentInicial();
            }

        } else {
            mostrarFragmentInicial();
        }
    }

    private void mostrarFragmentInicial() {
        FragmentInicial simpleFragment = FragmentInicial.newInstance();

        // Crear la transacción
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Agregar el fragmento
        fragmentTransaction.add(R.id.container_inicial,simpleFragment).addToBackStack(null).commit();
    }

    private void cerrarFragmentInicial() {
        // Crear la transacción
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentInicial inicial = (FragmentInicial) fragmentManager.findFragmentById(R.id.container_inicial);

        if (inicial != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(inicial).commit();
        }
    }

    private void mostrarFragemtPreguntas() {
        cerrarFragmentInicial();
        FragmentPreguntas fgPreg = FragmentPreguntas.newInstance(mRadioButtonChoice, 1);

        // Crear la transacción
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Agregar el fragmento
        fragmentTransaction.add(R.id.container_preguntas,fgPreg).addToBackStack(null).commit();
        isFragmentDisplayed = true;
    }

    public void onSaveInstanceState(Bundle savedInstancestate){
        savedInstancestate.putBoolean(STATE_FRAGMENT,isFragmentDisplayed);
        savedInstancestate.putString(SELECTED_QUESTIONARY, questionary);
         savedInstancestate.putInt(CURRENT_QUESTION, numQuestion);
        super.onSaveInstanceState(savedInstancestate);
    }

    @Override
    public void onButtonChoice(String choice) {
        questionary = choice;
        mostrarFragemtPreguntas();
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        mRadioButtonChoice = choice;
    }

    public void setQuestion(int num) {
        numQuestion = num;
    }

    public String getQuestion() {
        int quest = (questionary == "a") ? 0 : 1;
        return questions.getQuestion(quest, numQuestion - 1);
    }

    public void checkQuestion() {
        int quest = (questionary == "a") ? 0 : 1;
        Boolean correctAnswer = questions.getAnswer(quest, numQuestion - 1);
        Boolean selectedAnswer = (mRadioButtonChoice == 0) ? true : false;
        if (correctAnswer == selectedAnswer) {
            Toast.makeText(this,"Correct answer",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Incorrect answer",Toast.LENGTH_SHORT).show();
        }
    }
}
