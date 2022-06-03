package com.example.reconocimientodevoz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView grabar;
    private static final int RECOGNISE_SPEECH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grabar = (TextView)findViewById(R.id.id_Voices);
    }

    //Estos método se encarga de ejecutar los servicios de voz de google
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case RECOGNISE_SPEECH_ACTIVITY:
                if(resultCode == RESULT_OK && null != data){
                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String str = speech.get(0);
                    grabar.setText(str);
                }
                break;
            default:
                break;
        }
    }

    public void speak(View view){
        Intent intentActionRecognozeSpeech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //Configuración del lenguaje en este caso el español-Mexico
        intentActionRecognozeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"es-MX");

        try {
            startActivityForResult(intentActionRecognozeSpeech,RECOGNISE_SPEECH_ACTIVITY);
        }catch (ActivityNotFoundException a){
            Toast.makeText(getApplicationContext(),"Tu dispositivo no soporta el reconocimiento por voz",Toast.LENGTH_SHORT).show();
        }
    }
}