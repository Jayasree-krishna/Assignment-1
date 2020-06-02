package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;
    TextView textView1,textView2;
    SeekBar seekBar;
    Button button;
    static ImageView imageView;
    ConnectivityManager mycon;
    NetworkInfo mynet;
    DownLoad_Image di;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker=(TimePicker)findViewById(R.id.time_picker);
        textView1=(TextView)findViewById(R.id.text_view1);
        textView2=(TextView)findViewById(R.id.update);
        seekBar=(SeekBar)findViewById(R.id.seekbar);
        button=(Button)findViewById(R.id.button);
        imageView=(ImageView)findViewById(R.id.imageView);

        mycon= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        mynet=mycon.getActiveNetworkInfo();



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView2.setText(""+progress+"/100");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "seekbar start tracking touch", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "seekbar stop tracking touch", Toast.LENGTH_SHORT).show();
            }
        });

        textView1.setText(" "+timePicker.getCurrentHour()+" : "+timePicker.getCurrentMinute());
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(MainActivity.this, "time changed", Toast.LENGTH_SHORT).show();
                if(hourOfDay<=12)
                {
                    textView1.setText(" "+hourOfDay+":"+minute+" AM");
                }
                else
                    textView1.setText(" "+(hourOfDay-12)+":"+minute+" PM");

            }
        });
    }

    public void DownLoadImage(View view) {
        if(mynet!=null && mynet.isConnected())
        {
            di=new DownLoad_Image(this);
            di.execute("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQgKwWAyPKjTM8LeY4B4nHvzGwVJqB-zMFy1sRU_dEqgUp4K-75&usqp=CAU");
        }
        else
        {
            Toast.makeText(MainActivity.this,"check network connection",Toast.LENGTH_LONG).show();
        }
    }
}
