package com.example.verisaklama;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    TextView textView1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editTextNumber);
        textView1 = findViewById(R.id.yas);

        sharedPreferences = this.getSharedPreferences("com.example.verisaklama", Context.MODE_PRIVATE);

        int storedAge = sharedPreferences.getInt("storedAge",0);

        if(storedAge == 0) {
            textView1.setText("Your age: " );
        } else {
            textView1.setText("Your age: " +storedAge);
        }
    }

    public void save(View view) {



        AlertDialog.Builder savealert = new AlertDialog.Builder(this);

        savealert.setTitle("Save");
        savealert.setMessage("Are you sure?");

        savealert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //save

                if (!editText1.getText().toString().matches("")) {
                    int age = Integer.parseInt(editText1.getText().toString());
                    textView1.setText("Yaşınız: " + age);

                    sharedPreferences.edit().putInt("storedAge",age).apply();
                }

                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
            }
        });

        savealert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Not save
                Toast.makeText(MainActivity.this, "Not saved", Toast.LENGTH_LONG).show();
            }
        });
        savealert.show();

    }

    public void delete (View view) {

       int storedData = sharedPreferences.getInt("storedAge", 0);


       AlertDialog.Builder deleteAlert = new AlertDialog.Builder(this);
       deleteAlert.setTitle("Delete");
       deleteAlert.setMessage("Are you sure?");

       deleteAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               //delete
               if (storedData != 0) {

                   sharedPreferences.edit().remove("storedAge").apply();
                   textView1.setText("Your age: " );
               }

               Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
           }
       });

       deleteAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               //not delete
               Toast.makeText(getApplicationContext(), "Data not deleted", Toast.LENGTH_LONG).show();
           }
       });

       deleteAlert.show();

    }























}