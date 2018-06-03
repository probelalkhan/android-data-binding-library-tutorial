package com.example.iambe.databindingexample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.iambe.databindingexample.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating binding object
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //creating model object
        final User user = new User();

        //binding the data
        binding.setUser(user);

        //reading data from firebase database
        FirebaseDatabase.getInstance().getReference("user")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        user.setName(dataSnapshot.child("name").getValue(String.class));
                        user.setAddress(dataSnapshot.child("address").getValue(String.class));
                        user.setEmail(dataSnapshot.child("email").getValue(String.class));
                        user.setImage(dataSnapshot.child("image").getValue(String.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }
}
