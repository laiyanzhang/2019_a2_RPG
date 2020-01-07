package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class ChooseActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_choose);
        button1=findViewById(R.id.bt_1);
        button2=findViewById(R.id.bt_2);
        initView();
    }
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

        }
    }

    private void initView() {
        final String DATABASE_PATH="data/data/"+ this.getPackageName() + "/databases/";
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    button1.setTextColor(Color.parseColor("#00BCD4"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    button1.setTextColor(Color.parseColor("#FFFFFF"));
                    Intent intent1=new Intent (ChooseActivity.this,CreateActivity.class);
                    startActivity(intent1);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
                return false;
            }
        });
        button2.setOnTouchListener(new View.OnTouchListener() {
            File file=new File(DATABASE_PATH);
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    button2.setTextColor(Color.parseColor("#00BCD4"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    button2.setTextColor(Color.parseColor("#FFFFFF"));
                    if(file.exists()){
                        Intent intent1=new Intent (ChooseActivity.this,GameActivity.class);
                        startActivity(intent1);
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                }
                return false;
            }
        });
    }

}
