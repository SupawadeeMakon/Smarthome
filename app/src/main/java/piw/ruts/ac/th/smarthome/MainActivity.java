package piw.ruts.ac.th.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.Gravity;


public class MainActivity extends AppCompatActivity {

    public Button BedRoom,LivingRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BedRoom = (Button) findViewById(R.id.BedRoom);

        BedRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, BedRoom.class);
                startActivity(intent);
                Toast toast =Toast.makeText(getBaseContext(), "Welcome to BedRoom", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 10, 100);
                toast.show();

            }
        });//end set button



    }


}

