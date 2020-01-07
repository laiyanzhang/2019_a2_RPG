package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1,button2,button3,button4,button5,button6;
    private Fragment mfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_fragment);
        initBottomLayout();
        mfragment=CharacterFragment.newInstance("角色","Character");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,mfragment).commit();
    }

    private void initBottomLayout() {
        button1=findViewById(R.id.bt_1);
        button2 = findViewById(R.id.bt_2);
        button3 = findViewById(R.id.bt_3);
        button4 = findViewById(R.id.bt_4);
        button5 = findViewById(R.id.bt_5);
        button6 = findViewById(R.id.bt_6);
        button1.setTextColor(Color.YELLOW);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                button1.setTextColor(Color.YELLOW);
                button2.setTextColor(Color.BLACK);
                button3.setTextColor(Color.BLACK);
                button4.setTextColor(Color.BLACK);
                button5.setTextColor(Color.BLACK);
                switchFragment(CharacterFragment.newInstance("角色", "Character"));
                break;
            case R.id.bt_2:
                button1.setTextColor(Color.BLACK);
                button2.setTextColor(Color.YELLOW);
                button3.setTextColor(Color.BLACK);
                button4.setTextColor(Color.BLACK);
                button5.setTextColor(Color.BLACK);
                switchFragment(EquipmentFragment.newInstance("装备", "Equipment"));
                break;
            case R.id.bt_3:
                button1.setTextColor(Color.BLACK);
                button2.setTextColor(Color.BLACK);
                button3.setTextColor(Color.YELLOW);
                button4.setTextColor(Color.BLACK);
                button5.setTextColor(Color.BLACK);
                switchFragment(SkillFragment.newInstance("技能", "Skill"));
                break;
            case R.id.bt_4:
                button1.setTextColor(Color.BLACK);
                button2.setTextColor(Color.BLACK);
                button3.setTextColor(Color.BLACK);
                button4.setTextColor(Color.YELLOW);
                button5.setTextColor(Color.BLACK);
                switchFragment(TaskFragment.newInstance("任务", "Task"));
                break;
            case R.id.bt_5:
                button1.setTextColor(Color.BLACK);
                button2.setTextColor(Color.BLACK);
                button3.setTextColor(Color.BLACK);
                button4.setTextColor(Color.BLACK);
                button5.setTextColor(Color.YELLOW);
                switchFragment(MapFragment.newInstance("地图", "Map"));
                break;
            case R.id.bt_6:
                Intent intent1 = new Intent(FragmentActivity.this, GameActivity.class);
                startActivity(intent1);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }


    private void switchFragment(Fragment fragment){
        if (mfragment!=fragment){
            if(!fragment.isAdded()){
                getSupportFragmentManager().beginTransaction().hide(mfragment)
                        .add(R.id.container,fragment).commit();
            }
            else{
                getSupportFragmentManager().beginTransaction()
                        .hide(mfragment).show(fragment).commit();
            }
            mfragment=fragment;
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
