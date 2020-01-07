package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Dongxue extends AppCompatActivity {
    private  PrintTextView mPrintTextView;
    private ImageView imageView;
    private Intent intent,intent1;
    private int time;
    private int flag;
    private SQLiteDatabase db;
    private CreateActivity.MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_dongxue);
        myHelper=new CreateActivity.MyHelper(this);
        intent=getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if(null!=bundle && bundle.get("flag")!=null){
                flag = Integer.parseInt((String) bundle.get("flag"));
            }
        }
        mPrintTextView = findViewById(R.id.pt_1);
        imageView=findViewById(R.id.iv_1);
        db=myHelper.getReadableDatabase();
        Cursor cursor1=db.query("yingxiong",null,null,null,null,null,null);
        cursor1.moveToFirst();
        cursor1.moveToNext();
        imageView.setBackgroundResource(cursor1.getInt(1));
        cursor1.close();
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
            case 4:
                mPrintTextView.setPrintText("前面好像有人。" , 150);
                mPrintTextView.startPrint();
                time=1200;
                flag=4;
                intent1=new Intent (Dongxue.this,Dongxue2.class);
                break;
            case 5:
                mPrintTextView.setPrintText("方舟核心果然在这里，先发信号弹给狄大人。（发送信号弹）回去看看他们到底有什么计划。" , 150);
                mPrintTextView.startPrint();
                time=6500;
                flag=5;
                intent1=new Intent (Dongxue.this,Dongxue2.class);
                break;
            case 6:
                mPrintTextView.setPrintText("不行，撑不住了。" , 150);
                mPrintTextView.startPrint();
                time=1500;
                flag=6;
                intent1=new Intent (Dongxue.this,Dongxue3.class);
                break;
            case 8:
                mPrintTextView.setPrintText("要是我说还好你信不信？" , 150);
                mPrintTextView.startPrint();
                time=2000;
                flag=8;
                intent1=new Intent (Dongxue.this,Dongxue2.class);
                break;
            case 9:
                mPrintTextView.setPrintText("好多了。" , 150);
                mPrintTextView.startPrint();
                time=1000;
                flag=9;
                intent1=new Intent (Dongxue.this,Dongxue2.class);
                break;
            case 10:
                mPrintTextView.setPrintText("好。" , 150);
                mPrintTextView.startPrint();
                db=myHelper.getWritableDatabase();
                ContentValues values1=new ContentValues();
                values1.put("jindu",10);
                db.update("juqing",values1,"_id=?",new String[]{"1"});
                db.close();
                time=500;
                flag=10;
                intent1=new Intent (Dongxue.this,GameActivity.class);
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
