package com.sriram.task2_adg;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.material.snackbar.Snackbar;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    Button dateButton;
    int yyyy;
    int mm;
    int dd;
    Button snackbar;
    Button progressbar;
    Button customDialog;
    private AVLoadingIndicatorView avi;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        snackbar = findViewById(R.id.snackbutton);
        dateButton = findViewById(R.id.datebutton);
        avi=(AVLoadingIndicatorView)findViewById(R.id.avi);
        avi.hide();
        progressbar = findViewById(R.id.progressBarButton);
        customDialog = findViewById(R.id.customDialog);
        Toast.makeText(getApplicationContext(),"time out",Toast.LENGTH_LONG);

        Calendar calendar = Calendar.getInstance();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                yyyy = calendar.get(calendar.YEAR);
                mm = calendar.get(calendar.MONTH);
                dd = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yyyy, int mm, int dd) {
                        calendar.set(Calendar.YEAR,yyyy);
                        calendar.set(Calendar.MONTH,mm);
                        calendar.set(Calendar.DAY_OF_MONTH,dd);
                        dateButton.setText(SimpleDateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime()));
                    }

                },yyyy,mm,dd);
                datePickerDialog.show();
            }
        });


        snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snakeBar = Snackbar.make(view, "You just clicked a Button",Snackbar.LENGTH_LONG);
                snakeBar.show();
                snakeBar.setAction("Show Toast", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),"This is a toast!",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });
        progressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avi.show();
                countDownTimer=new CountDownTimer(5000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        avi.hide();
                    }
                }.start();
            }
        });


    customDialog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openDialog();

        }
    });
    }
    public void openDialog(){
        AlertDialog.Builder blder=new AlertDialog.Builder(this,R.style.AlertTheme);

        blder.setTitle("Hello");
        blder.setMessage("Hippity Hoppity Ho Ho HO");
        blder.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );
        blder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );
        AlertDialog all=blder.create();
        all.show();
    }


}