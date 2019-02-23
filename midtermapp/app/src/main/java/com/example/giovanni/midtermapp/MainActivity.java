package com.example.giovanni.midtermapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "touchLog.txt";
    ArrayList<String> logList = new ArrayList<String>();
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/aaLog";
    Button log;
    Toast toastMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log =(Button)findViewById(R.id.logBtn);

        // File dir = new File(path);
        //dir.mkdir();
        //  File logFile = new File (path+"/log.txt");

        //________________________________________
        final ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
        final View touchView = findViewById(R.id.viewPager);

        //________________________________________

        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String logstr = "Touch coordinates: "+" X:"+
                        String.valueOf(event.getX()) + " - Y:" + String.valueOf(event.getY());
                Log.i("inputLog",logstr );
                if (toastMessage!= null) {
                    toastMessage.cancel();
                }
                toastMessage= Toast.makeText(MainActivity.this, logstr, Toast.LENGTH_SHORT);
                toastMessage.show();
                logList.add(logstr);
                return false;
            }
        });
    }

    public void logView(View v)
    {
        String temp="";
        for (String object: logList) {
            temp+=object + System.getProperty("line.separator");
        }

        writeToLog(temp);
       // openFile();
        Intent intent = new Intent(this, inputLog.class);

               this.startActivity(intent);
    }


    public void writeToLog(String string)
    {
        FileOutputStream fos = null;
        try {
            fos=openFileOutput(FILE_NAME,MODE_PRIVATE);
            fos.write(string.getBytes());
            toastMessage= Toast.makeText(MainActivity.this, "Log saved to " + getFilesDir() +"/"+ FILE_NAME, Toast.LENGTH_LONG);
            toastMessage.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos==null)
            {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }



}
