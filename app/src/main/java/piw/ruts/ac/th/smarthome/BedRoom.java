package piw.ruts.ac.th.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

public class BedRoom extends AppCompatActivity {


    public FirebaseDatabase firebaseDatabase,databaseText;
    public DatabaseReference databaseReference_led1,datagetFirebase;
    private static final String TAG ="LED Control";
    public Button button_led;
    public Integer value,value_refer;
    public TextView TxtMyData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_room);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference_led1 =firebaseDatabase.getReference("Home/BedRoom/Led1");

        button_led=(Button) findViewById(R.id.BRLed1);

        databaseReference_led1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value=dataSnapshot.getValue(Integer.class);
                Log.d(TAG,"Value is :" + value);
                if (value==1){
                    button_led.setText("LED1 ON");
                    value_refer=0;
                }
                else {
                    button_led.setText("LED1 OFF");
                    value_refer=1;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w(TAG,"Error:", databaseError.toException());
            }
        });

        button_led.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference_led1.setValue(value_refer);
            }
        });

        //Get data from Sensor

        //ประกาศ id ของ textView
        TxtMyData = (TextView) findViewById(R.id.tetMydata);

        //สร้างตัวรับข้อมูล Firebase
        databaseText = FirebaseDatabase.getInstance();
        datagetFirebase = databaseText.getReference();
        datagetFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {//เรียกข้อมูลที่เกิดการเปลี่ยนแปลงทุกครั้งของค่าข้อมูลใน Path ที่อ้างถึง
                Map map = (Map)dataSnapshot.getValue() ; //ดึงค่าจาก Firebase
                String data1 =String.valueOf(map.get("MyData")); //ดึงค่าที่อยู่ใน Path
                TxtMyData.setText(data1);//แสดงค่า

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {//ไม่สามารถอ่านค่าข้อมูลในdatabase ได้

            }
        });


    }



}
