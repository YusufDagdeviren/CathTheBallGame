package com.yusufdagdeviren.catchtheball;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    TextView textView;
    TextView textView2;
    Runnable runnable;
    Handler handler;
    int score = 0;
    Random rnd = new Random();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);



        invisibility();








        visibility();

         new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {

            textView.setText("Remaining: "+ l/1000);


            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                textView2.setText("Score: "+score);
                invisibility();
                Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_LONG).show();

                alertDialog();
            }
        }.start();




    }
    public void alertDialog(){

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this); //If the context is not MainActivity.this here, it will throw an error
        alert.setTitle("Game Repeat");
        alert.setMessage("Do you want to play again?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Exit the game",Toast.LENGTH_LONG).show();//2
                finish();

            }
        });
        alert.show();



    }


    public void click(View view){

        score++;
        textView2.setText("Score: "+score);

    }

    public void invisibility(){

        for (ImageView image : imageArray){

            image.setVisibility(View.INVISIBLE);

        }


    }

    public void visibility(){


        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);



            }
        };

        handler.post(runnable);


    }





}