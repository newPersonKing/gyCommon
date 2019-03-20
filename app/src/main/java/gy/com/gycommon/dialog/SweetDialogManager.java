package gy.com.gycommon.dialog;

import android.content.Context;
import android.os.CountDownTimer;

import cn.pedant.SweetAlert.SweetAlertDialog;
import gy.com.gycommon.R;
import gy.com.gycommon.until.StringUtil;

public class SweetDialogManager {


    private static int i=0;
    public static void getColorDialog(final Context context){
        final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading");
        pDialog.show();
        pDialog.setCancelable(false);

        new CountDownTimer(800 * 7, 800) {
            public void onTick(long millisUntilFinished) {
                // you can change the progress bar color by ProgressHelper every 800 millis
                i++;
                switch (i){
                    case 0:
                        pDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.blue_btn_bg_color));
                        break;
                    case 1:
                        pDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.material_deep_teal_50));
                        break;
                    case 2:
                        pDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.success_stroke_color));
                        break;
                    case 3:
                        pDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.material_deep_teal_20));
                        break;
                    case 4:
                        pDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.material_blue_grey_80));
                        break;
                    case 5:
                        pDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.warning_stroke_color));
                        break;
                    case 6:
                        pDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.success_stroke_color));
                        break;
                }
            }

            public void onFinish() {
                i = -1;
                pDialog.setTitleText("Success!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            }
        }.start();
    }

    public void showSweetDialog(Context context,String titleText,String confirmText,String cancleText,String containText,int type){

        SweetAlertDialog  sweetAlertDialog = new SweetAlertDialog(context);

        if(type!=-1){
            sweetAlertDialog.changeAlertType(type);
        }

        if(!StringUtil.isEmpty(confirmText)){
            sweetAlertDialog.setConfirmText(confirmText);
        }

        if(!StringUtil.isEmpty(cancleText)){
            sweetAlertDialog.setCancelText(cancleText);
        }
        sweetAlertDialog.setTitleText(titleText);
        sweetAlertDialog.setContentText(cancleText);
        sweetAlertDialog.show();
    }

}
