package com.example.breaktime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SelectedItem extends AppCompatActivity {
    private ImageView imageView;
    private TextView date,cupName,cupNumbers,pr,customizePR;
    private Button plus,moins;
    private Intent intent;
    private String cupNamee;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String m_Text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("cafe");

        imageView = (ImageView) findViewById(R.id.imageView);
        plus = (Button) findViewById(R.id.plus);
        moins = (Button) findViewById(R.id.moins);
        intent=getIntent();
        cupNamee = intent.getStringExtra("cupNamee");
        cupName = (TextView) findViewById(R.id.cupName);
        cupNumbers = (TextView) findViewById(R.id.cupNumbers);
        pr = (TextView) findViewById(R.id.pr);
        customizePR = (TextView) findViewById(R.id.customizePR);
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        if (!cupNamee.isEmpty()){
            cupName.setText(cupNamee);
            imageView.setImageBitmap(bmp);
            getNumberAndPrice(cupNamee);
        }

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cupNamee.isEmpty()){
                    String coffe = cupName.getText().toString();
                    AddCoffe(coffe);
                }
            }
        });
        moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cupNamee.isEmpty()){
                    String coffe = cupName.getText().toString();
                    MoinsCoffe(coffe);
                }
            }
        });

        customizePR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cupNamee.isEmpty()){
                    String coffe = cupName.getText().toString();
                    MoinsCoffe(coffe);
                }
            }
        });
        customizePR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coffe = cupName.getText().toString();
                dialogPrice(coffe);
            }
        });

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        date = (TextView) findViewById(R.id.date);
        date.setText(formattedDate);


    }

    private void dialogPrice(String coffe) {
        //Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modify Price");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                modifyPrice(m_Text,coffe);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void modifyPrice(String m_Text, String coffe) {
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
                            if (coffeName.toLowerCase().equals(coffe.toLowerCase())){
                                int x = Integer.parseInt(m_Text);
                                if ((x >=0)){
                                    String newNumber = String.valueOf(x);
                                    myRef.child(key).child("price").setValue(newNumber);
                                    pr.setText(newNumber);
                                    Log.i("changeeeeed","priceeeeee "+newNumber);
                                }else{
                                    Toast.makeText(SelectedItem.this, "Coffe cup price can't be Negative !", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }
        );
    }

    private void AddCoffe(String coffe) {
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
                            Log.i("Foundedddddddd","heyyyyyy");
                            if (coffeName.toLowerCase().equals(coffe.toLowerCase())){
                                int x = Integer.parseInt(number);
                                x++;
                                String newNumber = String.valueOf(x);
                                myRef.child(key).child("number").setValue(newNumber);
                                cupNumbers.setText(newNumber);
                            }


                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }
        );
    }
    private void MoinsCoffe(String coffe) {
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
                            Log.i("Foundedddddddd","heyyyyyy");
                            if (coffeName.toLowerCase().equals(coffe.toLowerCase())){
                                int x = Integer.parseInt(number);
                                if ((x >0)){
                                    x--;
                                    String newNumber = String.valueOf(x);
                                    myRef.child(key).child("number").setValue(newNumber);
                                    cupNumbers.setText(newNumber);
                                }else{
                                    Toast.makeText(SelectedItem.this, "Coffe cup numbers already Null !", Toast.LENGTH_LONG).show();
                                }

                            }


                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }
        );
    }
    private void getNumberAndPrice(String cupNamee) {
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
                            //String key = String.valueOf(next.getKey());
                            Log.i("Foundedddddddd","heyyyyyy");
                            if (coffeName.toLowerCase().equals(cupNamee.toLowerCase())){
                                pr.setText(price);
                                cupNumbers.setText(number);
                                Log.i("name",coffeName);
                                Log.i("price",price);
                                Log.i("number",number);
                            }


                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }
        );
    }
}