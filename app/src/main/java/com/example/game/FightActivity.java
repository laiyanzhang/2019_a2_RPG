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

public class FightActivity extends AppCompatActivity {
    private static final int COMPLETED = 0;
    private static final int COMPLETED1 = 1;
    private static final int COMPLETED2 = 2;
    private static final int COMPLETED3 = 3;
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
    private TextView textView1,textView2,textView3;
    private ProgressBar progressBar1,progressBar2;
    private String num1,num2;
    private int flag=1;
    private int id;
    private int xue;
    private Intent intent;
    private SQLiteDatabase db;
    private CreateActivity.MyHelper myHelper;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            num2=textView2.getText().toString();
            if (msg.what == COMPLETED) {
                textView3.setText("你发动普攻\n对敌人造成250点伤害");
                num2=String.valueOf(Integer.valueOf(num2)-250);
                textView2.setText(num2);
                progressBar2.setProgress(Integer.valueOf(num2)*100/1000);
            }
            if(msg.what ==COMPLETED1){
                textView3.setText("敌人发动普攻\n对你造成200点伤害");
                num1=textView1.getText().toString();
                num1=String.valueOf(Integer.valueOf(num1)-200);
                textView1.setText(num1);
                progressBar1.setProgress(Integer.valueOf(num1)*100/xue);
            }
            if(msg.what ==COMPLETED2){
                textView3.setText("你发动普攻\n对敌人造成250点伤害");
                num2=String.valueOf(Integer.valueOf(num2)-250);
                textView2.setText(num2);
                progressBar2.setProgress(Integer.valueOf(num2)*100/1000);
            }
            if(msg.what ==COMPLETED3){
                textView3.setText("你发动一技能\n对敌人造成500点伤害");
                imageView5.setBackgroundResource(id);
                num2=String.valueOf(Integer.valueOf(num2)-500);
                textView2.setText(num2);
                progressBar2.setProgress(Integer.valueOf(num2)*100/1000);
                db=myHelper.getWritableDatabase();
                ContentValues values1=new ContentValues();
                ContentValues values2=new ContentValues();
                ContentValues values3=new ContentValues();
                values1.put("xueliang",Integer.valueOf(textView1.getText().toString()));
                values2.put("jingyan",50);
                values3.put("jindu",2);
                db.update("shuxing",values1,"_id=?",new String[]{"1"});
                db.update("jineng",values2,"_id=?",new String[]{"1"});
                db.update("juqing",values3,"_id=?",new String[]{"1"});
                db.close();
                intent=new Intent (FightActivity.this,GameActivity.class);
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
        setContentView(R.layout.activity_fight);
        myHelper=new CreateActivity.MyHelper(this);
        db=myHelper.getReadableDatabase();
        Cursor cursor1=db.query("shuxing",null,null,null,null,null,null);
        cursor1.moveToFirst();
        xue=(cursor1.getInt(2));
        num1=String.valueOf(cursor1.getInt(10));
        cursor1.close();
        Cursor cursor2=db.query("jineng",null,null,null,null,null,null);
        cursor2.moveToFirst();
        id=(cursor2.getInt(1));
        cursor2.close();
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
        Animation alpha2= AnimationUtils.loadAnimation(this,R.anim.huadong1);
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
        timer.schedule(task,4*1000);//此处的Delay可以是3*1000，代表三秒
        Timer timer3=new Timer();
        TimerTask task3=new TimerTask()
        {
            @Override
            public void run(){
                Message msg = new Message();
                msg.what = COMPLETED1;
                handler.sendMessage(msg);
            }
        };
        timer3.schedule(task3,5*1000);//此处的Delay可以是3*1000，代表三秒
        Timer timer1=new Timer();
        TimerTask task1=new TimerTask()
        {
            @Override
            public void run(){
                Message msg = new Message();
                msg.what = COMPLETED2;
                handler.sendMessage(msg);
            }
        };
        timer1.schedule(task1,8*1000);//此处的Delay可以是3*1000，代表三秒
        Timer timer4=new Timer();
        TimerTask task4=new TimerTask()
        {
            @Override
            public void run(){
                Message msg = new Message();
                msg.what = COMPLETED1;
                handler.sendMessage(msg);
            }
        };
        timer4.schedule(task4,10*1000);//此处的Delay可以是3*1000，代表三秒
        Timer timer2=new Timer();
        TimerTask task2=new TimerTask()
        {
            @Override
            public void run(){
                Message msg = new Message();
                msg.what = COMPLETED3;
                handler.sendMessage(msg);

            }
        };
        timer2.schedule(task2,12*1000);//此处的Delay可以是3*1000，代表三秒
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
