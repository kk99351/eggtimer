package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekbar;
    MediaPlayer mediaPlayer;
    Button button;
    CountDownTimer ctt;
    Boolean counterisActive = false;
    public void resettimer(){
        counterisActive = false;
        textView.setText("0:30");
        seekbar.setProgress(30);
        seekbar.setEnabled(true);
        button.setText("Start");
    }
    public void updatetimer(int i){
        int minutes = (int) i/60;
        int seconds = i - minutes*60;
        String secondstr = Integer.toString(seconds);
        if (seconds == 0){
            secondstr = "00";
        }
        else if (seconds <= 9){
            secondstr = "0" + secondstr;
        }
        textView.setText(Integer.toString(minutes)+":"+secondstr);
    }
    public void controltimer(View view){
        if (counterisActive == false){
            counterisActive = true;
            seekbar.setEnabled(false);
            button.setText("Stop");
            ctt= new CountDownTimer(seekbar.getProgress()*1000, 1000){
                @Override
                public void onTick(long millisec){
                    updatetimer((int) millisec / 1000);
                }
                public void onFinish(){
                    textView.setText("0:00");
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.air);
                    mediaPlayer.start();
                    resettimer();
                }
            }.start();
        }
        else{
            ctt.cancel();
            resettimer();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        textView = (TextView)findViewById(R.id.textt);
        button = (Button) findViewById(R.id.button);
        seekbar.setMax(600);
        seekbar.setProgress(30);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updatetimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}