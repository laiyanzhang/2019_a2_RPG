package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class FightActivity2 extends AppCompatActivity {
    private static final int COMPLETED = 0;
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7;
    private TextView textView1,textView2,textView3;
    private ProgressBar progressBar1,progressBar2;
    private int xue;
    private String num1;
    private SQLiteDatabase db;
    private CreateActivity.MyHelper myHelper;
    private Intent intent;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETED) {
                textView3.setText("敌人秒杀了你");
                textView1.setText("0");
                progressBar1.setProgress(0);
                db=myHelper.getWritableDatabase();
                ContentValues values1=new ContentValues();
                ContentValues values2=new ContentValues();
                values1.put("xueliang",0);
                values2.put("jindu",6);
                db.update("shuxing",values1,"_id=?",new String[]{"1"});
                db.update("juqing",values2,"_id=?",new String[]{"1"});
                db.close();
                intent=new Intent (FightActivity2.this,Dongxue.class);
                intent.putExtra("flag",Integer.toString(6));
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_fight2);
        myHelper=new CreateActivity.MyHelper(this);
        imageView1=findViewById(R.id.iv_1);
        imageView2=findViewById(R.id.iv_2);
        imageView3=findViewById(R.id.iv_3);
        imageView4=findViewById(R.id.iv_4);
        imageView5=findViewById(R.id.iv_5);
        textView1=findViewById(R.id.tv_1);
        textView2=findViewById(R.id.tv_2);
        textView3=findViewById(R.id.tv_3);
        progressBar1=findViewById(R.id.pb_1);
        progressBar2=findViewById(R.id.pb_2);
        db=myHelper.getReadableDatabase();
        Cursor cursor1=db.query("shuxing",null,null,null,null,null,null);
        cursor1.moveToFirst();
        xue=(cursor1.getInt(2));
        num1=String.valueOf(cursor1.getInt(10));
        cursor1.close();
        textView1.setText(num1);
        db=myHelper.getReadableDatabase();
        Cursor cursor3=db.query("yingxiong",null,null,null,null,null,null);
        cursor3.moveToFirst();
        imageView3.setBackgroundResource(cursor3.getInt(1));
        cursor3.moveToNext();
        imageView4.setBackgroundResource(cursor3.getInt(1));
        cursor3.close();
        progressBar1.setProgress(Integer.valueOf(num1)*100/xue);
        Animation alpha1= AnimationUtils.loadAnimation(this,R.anim.huadong);
        Animation alpha2= AnimationUtils.loadAnimation(this,R.anim.huadong2);
        imageView1.startAnimation(alpha1);
        imageView2.startAnimation(alpha2);
        Timer timer=new Timer();
        TimerTask task=new TimerTask()
        {
            @Override
            public void run(){
                Message msg = new Message();
                msg.what = COMPLETED;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(task,3*1000);//此处的Delay可以是3*1000，代表三秒
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
}
