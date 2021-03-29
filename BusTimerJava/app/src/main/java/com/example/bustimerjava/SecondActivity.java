package com.example.bustimerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public static int busSpan;
    View checker;
    // создание связного списка
    LinkedList<String> userSpan = new LinkedList<>();
    int variableSpan = 0;
    List<String> hours = new ArrayList<String>();
    int variableHours = 6;
    DateFormat df1 = new SimpleDateFormat("HH");
    DateFormat df2 = new SimpleDateFormat("mm");
    DateFormat df3 = new SimpleDateFormat("HH:mm");
    View curBus;
    View nextBus;


    public void confirm_btn(View view){

        checker = findViewById(R.id.checkBoxSpan);
        busSpan = Integer.parseInt(((EditText) checker).getText().toString()); //получение интервала хождение автобусов

        if (busSpan > 60)
            Toast.makeText(this, "Интервал не может быть более 60 минут.", Toast.LENGTH_SHORT).show();
        else
            setContentView(R.layout.activity_second_main);

        // заполнение "userSpan"
        while(variableSpan < 60) {
            if (variableSpan < 60)
                if(variableSpan < 10)
                    userSpan.add("0" + variableSpan);
                else
                    userSpan.add(String.valueOf(variableSpan));
            else
                break;
            variableSpan += busSpan;
        }

        // заполнение "Hours"
        while (variableHours <= 22){
            if (variableHours < 10)
                hours.add("0" + variableHours);
            else
                hours.add(String.valueOf(variableHours));
            variableHours++;
        }

    }

    @SuppressLint("SetTextI18n")
    public void refresh_btn(View view){
        boolean found1 = false;

        // получение данных о текущем времени
        String curHour = df1.format(Calendar.getInstance().getTime());
        String curMinute = df2.format(Calendar.getInstance().getTime());
        String curTime = df3.format(Calendar.getInstance().getTime());
        curBus = findViewById(R.id.currentBus);
        nextBus = findViewById(R.id.nextBus);

        for (int i = 0; i < hours.size(); i++){
            if (curHour.equals(hours.get(i)))
                found1 = true;
            }


        if (found1) {
            for (int i = 0; i < userSpan.size(); i++){

                // обработка межчасовых интервалов
                if (Integer.parseInt(userSpan.get(i)) > Integer.parseInt(curMinute) && Integer.parseInt(userSpan.get(i - 1)) < Integer.parseInt(curMinute)) {
                    ((TextView) curBus).setText(curHour + ":" + userSpan.get(i));
                    ((TextView) nextBus).setText(curHour + ":" + userSpan.get(i + 1));
                    System.out.println(userSpan.get(i + 1));
                }

                else if (Integer.parseInt(curMinute) > Integer.parseInt(userSpan.getLast())){
                    ((TextView) curBus).setText(curHour + ":" + userSpan.get(i));
                    for (int j = 0; j < hours.size(); j++){
                        if (curHour.equals(hours.get(j)) && !hours.get(j).equals(hours.get(16))){
                            ((TextView) nextBus).setText(hours.get(j+1) + ":" + userSpan.getFirst());
                        }
                        if (curHour.equals(hours.get(j)) && hours.get(j).equals(hours.get(16))){
                            ((TextView) nextBus).setText(hours.get(0) + ":" + userSpan.getFirst());
                        }
                    }
                }

                // конец часа (не последнего)
                /*else if (userSpan.getLast().equals(userSpan.get(i)) && Integer.parseInt(userSpan.get(i)) >= Integer.parseInt(curMinute)){
                    ((TextView) curBus).setText(curHour + ":" + userSpan.get(i));
                    for (int j = 0; j < hours.size(); j++){
                        if (curHour.equals(hours.get(j)) && !hours.get(j).equals(hours.get(16))){
                            ((TextView) nextBus).setText(hours.get(j+1) + ":" + userSpan.getFirst());
                        }
                        if (curHour.equals(hours.get(j)) && hours.get(j).equals(hours.get(16))){
                            ((TextView) nextBus).setText(hours.get(0) + ":" + userSpan.getFirst());
                        }
                    }
                    System.out.println(userSpan.get(i));
                }*/
            }
        }

            // обработка не рабочего времени
            else if (!found1) {
                ((TextView) curBus).setText(hours.get(0) + ":" + userSpan.getFirst());
                ((TextView) nextBus).setText(hours.get(0) + ":" + userSpan.get(1));
            }

        // обработка последнего часа
        if (curTime.startsWith(hours.get(16))) {
            ((TextView) curBus).setText(hours.get(0) + ":" + userSpan.getFirst());
            ((TextView) nextBus).setText(hours.get(0) + ":" + userSpan.get(1));
        }
    }

}