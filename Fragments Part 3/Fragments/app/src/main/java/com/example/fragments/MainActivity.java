package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener{

    private int mRadioButtonChoice = 2;
    private Button mButton;
    private boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.open_button);


        if (savedInstanceState != null){
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);

            if (isFragmentDisplayed){
                mButton.setText(R.string.close);
            }

        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isFragmentDisplayed){
                    displayFragment();
                }
                else
                    closeFragment();

            }
        });



    }

    // Para mostrar el Fragmento
    public void displayFragment(){

        SimpleFragment simpleFragment = SimpleFragment.newInstance(mRadioButtonChoice);


        // Crear la transacción
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Agregar el fragmento
        fragmentTransaction.add(R.id.fragment_container,simpleFragment).addToBackStack(null).commit();
        mButton.setText(R.string.close);
        isFragmentDisplayed = true;

    }

    public void onSaveInstanceState(Bundle savedInstancestate){

        savedInstancestate.putBoolean(STATE_FRAGMENT,isFragmentDisplayed);
        super.onSaveInstanceState(savedInstancestate);

    }

    public void closeFragment(){

        // Crear la transacción
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);

        if (simpleFragment != null){

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();

        }

        // Actualizamos el texto del boton

        mButton.setText(R.string.open);
        isFragmentDisplayed = false;

    }


    @Override
    public void onRadioButtonChoice(int choice) {
        mRadioButtonChoice = choice;
        Toast.makeText(this,"Choice is " + Integer.toString(choice),Toast.LENGTH_SHORT).show();
    }
}
