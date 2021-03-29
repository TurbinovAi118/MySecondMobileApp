package com.example.bustimerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static String bus = "";
    public View checker;
    public String checkBusNum;
    View busChecker;
    View textBtn;
    String busNumChecker = "";


    public void addBus(View view) {

        setContentView(R.layout.bus_add_tab);

    }

    public  void confirmBut(View view){

        checker = findViewById(R.id.checkBoxNum);
        checkBusNum = ((EditText) checker).getText().toString();

        if (checkBusNum.isEmpty())
            Toast.makeText(this, "Данные введены неверно", Toast.LENGTH_SHORT).show();
        else
            bus = checkBusNum;


        setContentView(R.layout.activity_main);

    }

    public void refresh(View view) {

        busChecker = findViewById(R.id.busButton);

        if (bus.equals("")) {
            Toast.makeText(this, "Автобус не определен", Toast.LENGTH_SHORT).show();
        } else
            ((TextView) busChecker).setText(bus);

    }

    public void redirectBtn(View view) {

        textBtn = findViewById(R.id.busButton);
        Intent intent = new Intent(this, SecondActivity.class);
        busNumChecker = ((TextView) textBtn).getText().toString();
        if (busNumChecker.equals(""))
            Toast.makeText(this, "Ошибка. Автобус не определен.", Toast.LENGTH_SHORT).show();
        else
            startActivity(intent);

    }

}
