package com.example.likhith.dt2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView resultTEXT;
    private String a="left";
    private String b="right";
    private String c="up";
    private String d="down";
    private ImageButton im;
    private float x=0,y=0;
    private String square="square";
    private String circle="circle";
    private String ring="ring";
    private RelativeLayout RL;
    private int X=580;
    private int Y=1180;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTEXT=(TextView)findViewById(R.id.TVresult);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        im=(ImageButton)findViewById(R.id.imageButton);
        RL=(RelativeLayout)findViewById(R.id.RL);
    }

    public void onButtonClick(View v) {
        if(v.getId()==R.id.imageButton)
        {
            promptSpeechInput();
        }
    }

    public void promptSpeechInput() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something!");

        try {
            startActivityForResult(i,100);
        }
                catch (ActivityNotFoundException a)
                {
                    Toast.makeText(MainActivity.this, "Sorry! Device does not support speech language!", Toast.LENGTH_LONG).show();
                }
    }

    public void onActivityResult(int request_code,int result_code, Intent i )
    {
        super.onActivityResult(request_code,result_code,i);

        switch (request_code)
        {
            case 100: if(result_code==RESULT_OK && i!=null)
            {
                ArrayList<String>result=i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if(result.get(0).compareTo(a)==0)
                {
                    x = im.getX();
                    if((x-10)<60)
                    {
                        x=X;
                        im.setX(x);
                        resultTEXT.setText("Wrapped");
                    }
                    else
                    {
                        x-=10;
                        im.setX(x);
                        resultTEXT.setText("Left");
                    }
                }


                if(result.get(0).compareTo(b)==0)
                {
                    x = im.getX();
                    if((x+10)>X)
                    {
                        x=60;
                        im.setX(x);
                        resultTEXT.setText("Wrapped");
                    }
                    else
                    {
                        x+=10;
                        im.setX(x);
                        resultTEXT.setText("Right!");
                    }
                }


                if(result.get(0).compareTo(c)==0)
                {
                    y = im.getY();
                    if((y-10)<60)
                    {
                        y=Y;
                        im.setY(y);
                        resultTEXT.setText("Wrapped");
                    }
                    else
                    {
                        y-=10;
                        im.setY(y);
                        resultTEXT.setText("Up!");
                    }

                }


                if(result.get(0).compareTo(d)==0)
                {
                    y = im.getY();
                    if((y+10)>Y)
                    {
                        y=60;
                        im.setY(y);
                        resultTEXT.setText("Wrapped");
                    }
                    else
                    {
                        y+=10;
                        im.setY(y);
                        resultTEXT.setText("Down");
                    }

                }
                if(result.get(0).compareTo(circle)==0)
                {
                    im.setImageResource(R.drawable.circle);
                    resultTEXT.setText("Circle");
                }
                if(result.get(0).compareTo(square)==0)
                {
                    im.setImageResource(R.drawable.square);
                    resultTEXT.setText("Square");
                }
                if(result.get(0).compareTo(ring)==0)
                {
                    im.setImageResource(R.drawable.ring);
                    resultTEXT.setText("Ring");
                }
            }break;
        }
    }
}


