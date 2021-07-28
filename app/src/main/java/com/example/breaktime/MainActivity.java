package com.example.breaktime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button recette;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView date,textView,textView2,textView5,textView3,textView4,textView6,textView3exp,textView4exp,textView6exp,textViewcr,textViewcrprix,textView55cr,textView66no,textView33no,
    textView44no,textView66ne,textView33ne,textView44ne,textView22222a,textView2222a,textView55a,textView66b,textView33b,textView44b,
            textView66c,textView33c,textView44r,textView33r,textView44c,textView55d,textView2222d,textView22222d,textView44e,textView33e,textView66e,
            textView66f,textView33f,textView44f,textView33,textView44,textView66,textView2222g,textView22222g,textView55g,textView66h,textView33h,textView66i,textView33i,
            textView44i,textView55j,textView2222,textView22222,textView55,textView66r,textView44h,textView2222j,textView22222j,textView66k,textView33k,textView44k,textView66l,textView33l,textView44l
    ;
    ImageView imageView2,imageView3,imageView3exp,imageView222cr,imageView33no,imageView33ne,imageView222a,imageView33b,imageView33c, imageView222d, imageView33e,
            imageView33f,imageView222g,imageView33h,imageView33i,imageView222j,imageView33k,imageView33l,imageView222,imageView33r,imageView33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getElements();
        recette = (Button) findViewById(R.id.recette);
        recette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Recette.class);
                startActivity(intent);
            }
        });

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        date = (TextView) findViewById(R.id.date);
        date.setText(formattedDate);



    }

    private void getElements() {
        database = FirebaseDatabase.getInstance();
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView5 = findViewById(R.id.textView5);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView3exp = findViewById(R.id.textView3exp);
        textView4exp = findViewById(R.id.textView4exp);
        textView6exp = findViewById(R.id.textView6exp);

        textViewcr = findViewById(R.id.textViewcr);
        textViewcrprix = findViewById(R.id.textViewcrprix);
        textView55cr = findViewById(R.id.textView55cr);
        textView33no = findViewById(R.id.textView33no);
        textView44no = findViewById(R.id.textView44no);
        textView66no = findViewById(R.id.textView66no);

        textView33ne = findViewById(R.id.textView33ne);
        textView44ne = findViewById(R.id.textView44ne);
        textView66ne = findViewById(R.id.textView66ne);

        textView2222a = findViewById(R.id.textView2222a);
        textView22222a = findViewById(R.id.textView22222a);
        textView55a = findViewById(R.id.textView55a);

        textView33b = findViewById(R.id.textView33b);
        textView44b = findViewById(R.id.textView44b);
        textView66b = findViewById(R.id.textView66b);

        textView33b = findViewById(R.id.textView33b);
        textView44b = findViewById(R.id.textView44b);
        textView66b = findViewById(R.id.textView66b);

        textView33c = findViewById(R.id.textView33c);
        textView44c = findViewById(R.id.textView44c);
        textView66c = findViewById(R.id.textView66c);

        textView2222d = findViewById(R.id.textView2222d);
        textView22222d = findViewById(R.id.textView22222d);
        textView55d = findViewById(R.id.textView55d);
        getExpress();
        getCapucin();
        getDirecte();
        getCaramel();
        getNoisette();
        getNescafe();
        getCappucino();
        getCappucinoSpecial();
        getTeaNormal();
        getTeaMonte();
        textView33e = findViewById(R.id.textView33e);
        textView44e = findViewById(R.id.textView44e);
        textView66e = findViewById(R.id.textView66e);
        getJus();
        textView33f = findViewById(R.id.textView33f);
        textView44f = findViewById(R.id.textView44f);
        textView66f = findViewById(R.id.textView66f);
        getJusDeSaison();
        textView2222g = findViewById(R.id.textView2222g);
        textView22222g = findViewById(R.id.textView22222g);
        textView55g = findViewById(R.id.textView55g);
        getEauPetit();
        textView33h = findViewById(R.id.textView33h);
        textView44h = findViewById(R.id.textView44h);
        textView66h = findViewById(R.id.textView66h);
        getEauGrand();
        textView33i = findViewById(R.id.textView33i);
        textView44i = findViewById(R.id.textView44i);
        textView66i = findViewById(R.id.textView66i);
        getCanette();
        textView2222j = findViewById(R.id.textView2222j);
        textView22222j = findViewById(R.id.textView22222j);
        textView55j = findViewById(R.id.textView55j);
        getPanini();
        textView33k = findViewById(R.id.textView33k);
        textView44k = findViewById(R.id.textView44k);
        textView66k = findViewById(R.id.textView66kk);
        getCroissante();
        textView33l = findViewById(R.id.textView33l);
        textView44l = findViewById(R.id.textView44l);
        textView66l = findViewById(R.id.textView66l);
        getDelice();
        textView2222 = findViewById(R.id.textView2222);
        textView22222 = findViewById(R.id.textView22222);
        textView55 = findViewById(R.id.textView55);
        getSoufflet();
        textView33r = findViewById(R.id.textView33r);
        textView44r = findViewById(R.id.textView44r);
        textView66r = findViewById(R.id.textView66r);
        getPatte();
        textView33 = findViewById(R.id.textView33);
        textView44 = findViewById(R.id.textView44);
        textView66 = findViewById(R.id.textView66);
        getVide();


        imageView2 = (ImageView)  findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.express);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView3 = (ImageView)  findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView3.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cappucin);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView3exp = (ImageView)  findViewById(R.id.imageView3exp);
        imageView3exp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView3exp.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.directe);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView222cr = (ImageView)  findViewById(R.id.imageView222cr);
        imageView222cr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textViewcr.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.caramel);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33no = (ImageView)  findViewById(R.id.imageView33no);
        imageView33no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView44no.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.noisette);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33ne = (ImageView)  findViewById(R.id.imageView33ne);
        imageView33ne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33ne.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.nescafe);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView222a = (ImageView)  findViewById(R.id.imageView222a);
        imageView222a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView2222a.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cappucino);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33b = (ImageView)  findViewById(R.id.imageView33b);
        imageView33b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33b.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cappucinospecial);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33c = (ImageView)  findViewById(R.id.imageView33c);
        imageView33c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33c.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.greentea);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView222d = (ImageView)  findViewById(R.id.imageView222d);
        imageView222d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView2222d.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.greentea1);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33e = (ImageView)  findViewById(R.id.imageView33e);
        imageView33e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33e.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.jus);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33f = (ImageView)  findViewById(R.id.imageView33f);
        imageView33f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33f.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.jusdesaison);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView222g = (ImageView)  findViewById(R.id.imageView222g);
        imageView222g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView2222g.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.eaupetit);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33h = (ImageView)  findViewById(R.id.imageView33h);
        imageView33h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33h.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.eaugrand);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33i = (ImageView)  findViewById(R.id.imageView33i);
        imageView33i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33i.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cola);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView222j = (ImageView)  findViewById(R.id.imageView222j);
        imageView222j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView2222j.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.panini);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33k = (ImageView)  findViewById(R.id.imageView33k);
        imageView33k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33k.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.croissant);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33l = (ImageView)  findViewById(R.id.imageView33l);
        imageView33l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33l.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cake);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });


        imageView222 = (ImageView)  findViewById(R.id.imageView222);
        imageView222.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView2222.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sandwich);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33r = (ImageView)  findViewById(R.id.imageView33r);
        imageView33r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33r.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.patte);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

        imageView33 = (ImageView)  findViewById(R.id.imageView33);
        imageView33.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent SelectedItemActivity = new Intent(MainActivity.this, SelectedItem.class);
                String cupName = textView33.getText().toString();
                SelectedItemActivity.putExtra("cupNamee",cupName);

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_trash);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                SelectedItemActivity.putExtra("picture", byteArray);

                startActivity(SelectedItemActivity);
            }
        });

    }

    private void getExpress() {
        myRef = database.getReference("cafe").child("express");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView2.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView5.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getCapucin() {
        myRef = database.getReference("cafe").child("capucin");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView3.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView4.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView6.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getDirecte() {

        myRef = database.getReference("cafe").child("directe");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView3exp.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView4exp.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView6exp.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getCaramel() {
        myRef = database.getReference("cafe").child("caramel");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textViewcr.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textViewcrprix.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView55cr.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getNoisette() {
        myRef = database.getReference("cafe").child("noisette");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33no.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44no.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66no.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getNescafe() {
        myRef = database.getReference("cafe").child("nescafe");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33ne.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44ne.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66ne.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getCappucino() {
        myRef = database.getReference("cafe").child("cappucino");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView2222a.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView22222a.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView55a.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getCappucinoSpecial() {
        myRef = database.getReference("cafe").child("cappucinospecial");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33b.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44b.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66b.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getTeaNormal() {
        myRef = database.getReference("cafe").child("teanormal");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33c.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44c.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66c.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getTeaMonte() {
        myRef = database.getReference("cafe").child("teamonte");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView2222d.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView22222d.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView55d.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getJus() {
        myRef = database.getReference("cafe").child("jus");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33e.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44e.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66e.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getJusDeSaison() {
        myRef = database.getReference("cafe").child("jusdesaison");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33f.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44f.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66f.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getEauPetit() {
        myRef = database.getReference("cafe").child("eaupetit");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView2222g.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView22222g.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView55g.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getEauGrand() {
        myRef = database.getReference("cafe").child("eaugrand");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33h.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44h.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66h.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getCanette() {
        myRef = database.getReference("cafe").child("canette");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33i.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44i.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66i.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getPanini() {
        myRef = database.getReference("cafe").child("panini");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView2222j.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView22222j.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView55j.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getCroissante() {
        myRef = database.getReference("cafe").child("croissante");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33k.setText(value);
                Log.i( "Value is: " , ""+value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44k.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66k.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getDelice() {
        myRef = database.getReference("cafe").child("delice");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33l.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44l.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66l.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getSoufflet() {
        myRef = database.getReference("cafe").child("soufflet");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView2222.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView22222.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView55.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getPatte() {
        myRef = database.getReference("cafe").child("patte");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33r.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44r.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66r.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
    private void getVide() {
        myRef = database.getReference("cafe").child("vide");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("name").getValue(String.class);
                textView33.setText(value);
                Log.i( "Value is: " , value);
                String price = dataSnapshot.child("price").getValue(String.class);
                textView44.setText(price);
                Log.i( "Price is: " , ""+price);
                String number = dataSnapshot.child("number").getValue(String.class);
                textView66.setText(number);
                Log.i( "Number is: " , ""+number);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "Failed to read value." , ""+error.toException());
            }
        });

    }
}