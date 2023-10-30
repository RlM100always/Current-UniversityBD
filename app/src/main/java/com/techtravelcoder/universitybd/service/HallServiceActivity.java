package com.techtravelcoder.universitybd.service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techtravelcoder.universitybd.R;
import com.techtravelcoder.universitybd.adapter.HallServiceAdapter;

import com.techtravelcoder.universitybd.model.HallServiceModel;


import java.util.ArrayList;

public class HallServiceActivity extends AppCompatActivity {

    RecyclerView hallRecyclerView;
    HallServiceAdapter hallServiceAdapter;
    ArrayList<HallServiceModel> list ;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_service);



        String uniName =getIntent().getStringExtra("name");
        Toast.makeText(this, ""+uniName, Toast.LENGTH_SHORT).show();


        hallRecyclerView=findViewById(R.id.hall_service_rv_id);
        list=new ArrayList<>();
        hallServiceAdapter=new HallServiceAdapter(this,list);

        databaseReference= FirebaseDatabase.getInstance().getReference("halls").child(uniName);
        hallRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hallRecyclerView.setAdapter(hallServiceAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    Toast.makeText(getApplicationContext(), "ryr", Toast.LENGTH_SHORT).show();

                    HallServiceModel hallServiceModel = snapshot1.getValue(HallServiceModel.class);
                    Toast.makeText(getApplicationContext(), "rrr", Toast.LENGTH_SHORT).show();

                    if(hallServiceModel != null){

                        list.add(hallServiceModel);

                    }
                }


                hallServiceAdapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

            }
        });






    }
}