package gy.com.gycommon;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.view.ViewAttachEvent;

import cn.pedant.SweetAlert.SweetAlertDialog;
import gy.com.gycommon.activity.TabFragmentActivity;
import gy.com.gycommon.base.BaseRxBindingActivity;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseRxBindingActivity {

    Button btn;
    AppCompatImageView ig_ic;
    EditText et_one,et_two;
    float lastX,lastY;
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_click);
        ig_ic = findViewById(R.id.ig_ic);
        et_one = findViewById(R.id.et_one);
        et_two = findViewById(R.id.et_two);

        RxOnClick(findViewById(R.id.btn_tab_fragment));

        RxOnClick(findViewById(R.id.btn_pre));

        RxOnLongClick(findViewById(R.id.btn_click),true);
        RxHovers(findViewById(R.id.btn_click),null);
        RxFocusChanges(findViewById(R.id.et_one));

        final ClipData clipData = ClipData.newIntent("label",new Intent());

        RxKeys(et_one);

        RxDrags(btn);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutID() {
        return 0;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    int i=0;
    public void jump(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btn_click:
                intent.setClass(this,TestActivity.class);
                startActivity(intent);

                break;
        }
    }

    @Override
    protected void onClick(View view) {
        Intent intent = new Intent();
           switch (view.getId()){
               case R.id.btn_tab_fragment:
                   intent.setClass(this,TabFragmentActivity.class);
                   startActivity(intent);
                   break;
               case R.id.btn_pre:
                   intent.setClass(this,PreActivity.class);
                   startActivity(intent);
                   break;
           }
    }

    @Override
    protected void onLongClick(View view) {

    }

    @Override
    protected void onHovers(MotionEvent motionEvent) {

    }
}
