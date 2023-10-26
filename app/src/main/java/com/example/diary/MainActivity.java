package com.example.diary;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // получаем экземпляр элемента ListView
        ListView listView = findViewById(R.id.records);

        // определяем строковый массив
        final String[] catNames = new String[] {
                "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
                "Томасина", "Кристина"
                //, "Пушок", "Дымка", "Кузя",
                //"Китти", "Масяня", "Симба",

        };

        // используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, catNames);
        listView.setAdapter(adapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d(TAG, "=== OnScroll: " + scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(TAG, "=== OnScroll: " + firstVisibleItem + ":" + visibleItemCount + ":" + totalItemCount);
            }
        });




/*
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("\t\t\t\t\t\t\t\t\t\tНовая запись");
        //alert.setMessage("Message");
        final EditText input = new EditText(this);
        input.setGravity(1);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                adapter.add(input.getText().toString());
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
*/


        Button addBut = findViewById(R.id.addRecord);
        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alert.show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //builder.setTitle("Подтверждение");
                builder.setTitle("\t\t\t\t\t\t\t\t\t\tНовая запись");
                final EditText input = new EditText(MainActivity.this);
                input.setGravity(1);
                builder.setView(input);

                builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_LONG).show();

                        ListView lv = findViewById(R.id.records);
                        ListAdapter la = lv.getAdapter();

                        String[] catNames = new String[la.getCount()+1];
                        for (int i = 0; i < la.getCount(); i++){
                            catNames[i] += la.getItem(i);
                            catNames[i] = catNames[i].substring(4);
                        }
                        catNames[la.getCount()] = input.getText().toString();

                        ArrayAdapter<String> newAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, catNames);
                        lv.setAdapter(newAdapter);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "Not Hello World!", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });











    }
}