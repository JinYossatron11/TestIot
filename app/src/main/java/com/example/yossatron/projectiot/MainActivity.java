package com.example.yossatron.projectiot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    public DatabaseReference myRof; /// การประกาศการสร้าง Event ในการหาค่าในฟิลใน Database
    private TextView nFirebasename;
    private TextView nFirebasename1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nFirebasename = (TextView) findViewById(R.id.firebaseusername); /// การประกาศแสดงผลการแสดง
        nFirebasename1 = (TextView) findViewById(R.id.firebaseusername1); /// การประกาศแสดงผลการแสดง
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRof = database.getReference();
        myRof.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.child("SensingAir").getValue();
                String ms = String.valueOf(map.get("ms")); /// การประกาศเข้าฟิลใน firebase
                String ra = String.valueOf(map.get("raindrop")); /// การประกาศเข้าฟิลใน firebase
                nFirebasename.setText(" Soil moisture = " + ms + " , Raindrop = " + ra);
                int numberms = Integer.parseInt(ms);
                int numberrain = Integer.parseInt(ra);
                if(numberms==250 && numberrain==20 ) {
                    nFirebasename1.setText(" Ready");
                    nFirebasename1.setBackgroundColor(10);
                }else{
                    nFirebasename1.setText("NO Ready");
                    nFirebasename1.setBackgroundColor(20);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
