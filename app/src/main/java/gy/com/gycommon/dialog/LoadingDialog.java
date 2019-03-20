package gy.com.gycommon.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gy.com.gycommon.R;
import gy.com.gycommon.until.StringUtil;

public class LoadingDialog extends Dialog implements View.OnClickListener{

    private final static String TAG = "LoadingDialog";

    private LinearLayout mNoBgLinely;
    private TextView mTipTxt;
    private String mTip;
    private Context loadingDialogContext;
    private RelativeLayout contain;

    public LoadingDialog( Context context,String Tip) {
        super(context,R.style.dialog_loading_view);
        loadingDialogContext = context;
        this.mTip = Tip;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        /*设置点击外侧消失*/
        setCanceledOnTouchOutside(true);
        mNoBgLinely =  findViewById(R.id.ll_loading);
        mNoBgLinely.setVisibility(View.VISIBLE);
        mTipTxt = findViewById(R.id.tip);
        if(!StringUtil.isEmpty(mTip)){
            mTipTxt.setText(mTip);
        }
        mNoBgLinely.setOnClickListener(this);
    }

    public void onDismiss() {
        if (this.isShowing()) {
            this.dismiss();
        }
    }


    @Override
    public void onClick(View v) {
        onDismiss();
    }

}
