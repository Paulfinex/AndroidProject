package com.example.giovanni.midtermapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class inputLog extends AppCompatActivity {

    TextView logview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputlog);
        logview = findViewById(R.id.logview);
        logview.setMovementMethod(new ScrollingMovementMethod());
        ArrayList<String> logList = openFile();
        for (String object: logList) {
            Log.i("inputLog",object+"_______" );
            logview.append(object+System.getProperty("line.separator"));
        }

        //editText.setText(openFile());
    }


    ArrayList<String> openFile()
    {
        ArrayList<String> logList =  new ArrayList<String>();
        FileInputStream fis = null;
        try {
            fis = openFileInput("touchLog.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while((text = br.readLine())!= null)
            {
                logList.add(text);
            }
            String temp = sb.toString();
            return logList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

