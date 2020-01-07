package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class qiyuanzhidi1 extends AppCompatActivity {
    private  PrintTextView mPrintTextView;
    private ImageView imageView;
    private SQLiteDatabase db;
    private CreateActivity.MyHelper myHelper;
    private int flag;
    private int time;
    private Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_qiyuanzhidi1);
        myHelper=new CreateActivity.MyHelper(this);
        db=myHelper.getReadableDatabase();
        Cursor cursor1=db.query("juqing",null,null,null,null,null,null);
        cursor1.moveToFirst();
        flag=(cursor1.getInt(1));
        cursor1.close();
        Cursor cursor2=db.query("yingxiong",null,null,null,null,null,null);
        cursor2.moveToFirst();
        cursor2.moveToNext();
        mPrintTextView = findViewById(R.id.pt_1);
        imageView=findViewById(R.id.iv_1);
        imageView.setBackgroundResource(cursor2.getInt(1));
        cursor2.close();
        db.close();
        Timer timer=new Timer();
        startPrint(mPrintTextView);
        TimerTask task=new TimerTask()
        {
            @Override
            public void run(){
                intent1.putExtra("flag",Integer.toString(flag));
                startActivity(intent1);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        };
        timer.schedule(task,time);
    }

    public void startPrint(View view) {
        switch(flag){
            case 1:
                mPrintTextView.setPrintText("元芳，方舟核心真的会在这里吗？我们都找了三天了。" , 150);
                mPrintTextView.startPrint();
                time=5000;
                flag=1;
                intent1=new Intent (qiyuanzhidi1.this,qiyuanzhidi2.class);
                break;
            case 2:
                mPrintTextView.setPrintText("好！相信我们还能再见。" , 150);
                mPrintTextView.startPrint();
                time=3000;
                flag=2;
                intent1=new Intent (qiyuanzhidi1.this,FightActivity.class);
                break;

        }

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
