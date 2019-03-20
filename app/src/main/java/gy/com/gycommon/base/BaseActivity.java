package gy.com.gycommon.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import gy.com.gycommon.R;
import gy.com.gycommon.constant.TransitionMode;
import gy.com.gycommon.dialog.LoadingDialog;
import gy.com.gycommon.manager.ActivityManager;
import gy.com.gycommon.until.StringUtil;

public abstract  class BaseActivity extends RxAppCompatActivity {

    protected String TAG = "BaseActivity";

    /*context*/
    protected Context mContext = null;

    /**
     * screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    /**
     * default toolbar
     * 设置自定义的toolbar
     */
    protected TextView tvTitle;
    protected TextView tvRight;
    protected Toolbar toolbar;
    protected FrameLayout toolbarLayout;
    private LinearLayout contentView;

    /**
     * dialog
     */
    protected LoadingDialog loadingDialog;
    protected SweetAlertDialog sweetAlertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        // activity manager
        ActivityManager.getInstance().addActivity(this);

        if(toggleOverridePendingTransition()){
           switch (getOverridePendingTransitionMode()){
               case LEFT:
                   overridePendingTransition(R.anim.left_in, R.anim.right_out);
                   break;
               case RIGHT:
                   overridePendingTransition(R.anim.right_in, R.anim.left_in);
                   break;
               case TOP:
                   overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
                   break;
               case BOTTOM:
                   overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
                   break;
               case SCALE:
                   overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                   break;
               case FADE:
                   overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                   break;
           }
        }

        // extras
        Bundle bundle = getIntent().getExtras();
        if(null != null){
              getBundleExtras(bundle);
        }

        // screen info
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;

        int layoutId = getLayoutID();
        if(layoutId!=0){
            setContentView(layoutId);
        }

        init();

        initRxPermissions();

        initViewsAndEvents(savedInstanceState);
    }


    @Override
    public void setContentView(int layoutResID) {

        if(isLoadDefaultTitleBar()){
            initToolBarView();
            /*添加toolbar到布局*/
            initContentView();
            contentView.addView(toolbarLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            LayoutInflater.from(this).inflate(layoutResID,contentView,true);
            super.addContentView(contentView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        }else{
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void finish() {
        super.finish();
        ActivityManager.getInstance().removeActivity(this);
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
    }

    protected void initToolBarView(){
        toolbarLayout = new FrameLayout(this);
        LayoutInflater.from(this).inflate(R.layout.layout_toolbar,toolbarLayout,true);
        tvTitle = (TextView) toolbarLayout.findViewById(R.id.tv_title);
        tvRight = (TextView) toolbarLayout.findViewById(R.id.tv_right);
        toolbar = (Toolbar) toolbarLayout.findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        initToolBarSet(actionBar);
    }

    protected void initToolBarSet(ActionBar actionBar){
        actionBar.setDisplayShowTitleEnabled(false);

        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    protected void initContentView() {
        contentView = new LinearLayout(this);
        contentView.setOrientation(LinearLayout.VERTICAL);
    }

    public void showLoadingDialog(String tips){
        if(!isFinishing()){
            if(null == loadingDialog){
                loadingDialog = new LoadingDialog(this,tips);
                loadingDialog.show();
            }else{
                loadingDialog.dismiss();
                loadingDialog = new LoadingDialog(this,tips);
                loadingDialog.show();
            }
        }
    }

    public void dismissLoadingDialog(){
        if(!isFinishing()&&loadingDialog!=null&&loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
    }

    protected Intent getGoIntent(Class<?> clazz) {
        return new Intent(this, clazz);
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = getGoIntent(clazz);
        startActivity(intent);
    }

    /**
     * startActivity with  flag
     * @param clazz
     * @param flag
     */
    protected void readyGo(Class<?> clazz, int flag) {
        Intent intent = getGoIntent(clazz);
        intent.setFlags(flag);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = getGoIntent(clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity with bundle and flag
     * @param clazz
     * @param bundle
     * @param flag
     */
    protected void readyGo(Class<?> clazz, Bundle bundle, int flag) {
        Intent intent = getGoIntent(clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        intent.setFlags(flag);
        startActivity(intent);
    }


    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = getGoIntent(clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = getGoIntent(clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = getGoIntent(clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /*初始化一些跟view 无关的属性*/
    protected abstract void init();

    /*是否开启过场动画*/
    protected  boolean toggleOverridePendingTransition(){
        return false;
    };

    /*开启过场动画 设置这里 选择过场动画*/
    protected  TransitionMode getOverridePendingTransitionMode(){
        return TransitionMode.LEFT;
    };

    /*获取页面传递的数据*/
    protected abstract void getBundleExtras(Bundle extras);

    /*获取显示页面ID*/
    protected abstract int getLayoutID();

    /*初始化view*/
    protected abstract void initViewsAndEvents(Bundle savedInstanceState);

    /*是否加载自定义的title*/
    protected  boolean isLoadDefaultTitleBar(){
        return false;
    };

    /*初始化权限申请*/
    protected  void initRxPermissions(){};

}
