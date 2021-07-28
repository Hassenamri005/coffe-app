package com.example.breaktime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class Recette extends AppCompatActivity {
    private TextView date,resrec;
    ImageView imageView4;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    int somme=0;
    AlertDialog.Builder builder;
    AlertDialog alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // read a message from database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("cafe");

        resrec = (TextView) findViewById(R.id.resrec);
        getRecette();
        builder = new AlertDialog.Builder(this);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();

            }
        });

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        date = (TextView) findViewById(R.id.date);
        date.setText(formattedDate);


    }

    private void delete() {
        //Setting message manually and performing action on button click
        builder.setMessage("Do you want to delete all coffe numbers ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        deleteAllCoffeeNumbers();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        //Creating dialog box
        alert = builder.create();
        //Setting the title manually
        alert.setTitle("Reset");
        alert.show();

        }

    private void deleteAllCoffeeNumbers() {
        // Read from the database
        myRef = database.getReference("cafe");
        Query query = myRef.orderByKey();
        query.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                        Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                        while (iterator.hasNext()) {
                            DataSnapshot next = (DataSnapshot) iterator.next();
                       /*     String coffeName = String.valueOf(next.child("").child("name").getValue());
                            String number = String.valueOf(next.child("").child("number").getValue());
                            String price = String.valueOf(next.child("").child("price").getValue());
                            String key = String.valueOf(next.getKey());

                        */
                            String key = String.valueOf(next.getKey());
                            myRef.child(key).child("number").setValue(String.valueOf(0));
                            resrec.setText("$ "+0);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }
        );
    }

    private void getRecette() {
        // Read from the database
        myRef = database.getReference("cafe");
        Query query = myRef.orderByKey();
        query.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                        Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                        while (iterator.hasNext()) {
                            DataSnapshot next = (DataSnapshot) iterator.next();
                            String coffeName = String.valueOf(next.child("").child("name").getValue());
                            String number = String.valueOf(next.child("").child("number").getValue());
                            String price = String.valueOf(next.child("").child("price").getValue());
                            String key = String.valueOf(next.getKey());

                            int n = Integer.parseInt(number);
                            int pr = Integer.parseInt(price);
                            somme = somme + (pr*n);
                            Log.i("sommmmmmmmmme",""+somme);
                            resrec.setText("$ "+somme);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }
        );

    }
}