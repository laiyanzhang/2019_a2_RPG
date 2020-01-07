package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2,button3,button4,button5;
    private Intent intent1;
    private int flag;
    private SQLiteDatabase db;
    private CreateActivity.MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_game);
        myHelper=new CreateActivity.MyHelper(this);
        db=myHelper.getReadableDatabase();
        Cursor cursor1=db.query("juqing",null,null,null,null,null,null);
        cursor1.moveToFirst();
        flag=(cursor1.getInt(1));
        cursor1.close();
        db.close();
        button1=findViewById(R.id.bt_1);
        button2=findViewById(R.id.bt_2);
        button3=findViewById(R.id.bt_3);
        button4=findViewById(R.id.bt_4);
        button5=findViewById(R.id.bt_5);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                intent1 = new Intent(GameActivity.this, FragmentActivity.class);
                startActivity(intent1);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.bt_2:
                if(flag==3){
                    flag=4;
                    intent1=new Intent (GameActivity.this,Dongxue.class);
                    intent1.putExtra("flag",Integer.toString(flag));
                    startActivity(intent1);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
                break;
            case R.id.bt_3:
                if(flag==2){
                    flag=3;
                    intent1=new Intent (GameActivity.this,qiyuanzhidi2.class);
                    intent1.putExtra("flag",Integer.toString(flag));
                    startActivity(intent1);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
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
