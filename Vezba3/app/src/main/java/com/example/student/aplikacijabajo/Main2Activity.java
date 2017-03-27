package com.example.student.aplikacijabajo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        adapter.add("Dutja");
        adapter.add("Leeman");
        adapter.add("VoojiTJ");
        adapter.add("Dziman");
        final ListView lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(adapter);
        final EditText et = (EditText) findViewById(R.id.editText);
        final Button dugme = (Button) findViewById(R.id.dugme);
        dugme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.add(et.getText().toString());
                et.setText("");
            }
        });
    }
}
