package gy.com.gycommon.base;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.DragEvent;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.view.ViewAttachEvent;
import com.jakewharton.rxbinding2.view.ViewScrollChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public abstract class BaseRxBindingActivity extends BaseActivity {


    private List<Disposable> disposables = new ArrayList<>();

    protected void RxOnClick(final View view){
        Disposable disposable = RxView.clicks(view)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        onClick(view);
                    }
                });
        disposables.add(disposable);
    }

    /*长按 是否起作用*/
    protected void RxOnLongClick(final View view, final boolean isCall){
        Disposable disposable = RxView.longClicks(view, new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return isCall;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                onLongClick(view);
            }
        });
        disposables.add(disposable);
    }

    /*第二个参数 判断 悬浮起作用的条件*/
    protected void RxHovers(View view,Predicate<MotionEvent> predicate){
        Disposable disposable;
        if(predicate == null){
            disposable = RxView.hovers(view).subscribe(new Consumer<MotionEvent>() {
                @Override
                public void accept(MotionEvent motionEvent) throws Exception {
                    onHovers(motionEvent);
                }
            });
        }else{
            disposable =  RxView.hovers(view, predicate).subscribe(new Consumer<MotionEvent>() {
                @Override
                public void accept(MotionEvent motionEvent) throws Exception {
                    onHovers(motionEvent);
                }
            });
        }
        disposables.add(disposable);
    }

    /*view 依赖到windows 完成时回调*/
    protected void RxAttachEvents(View view){
        Disposable disposable =  RxView.attachEvents(view)
                .subscribe(new Consumer<ViewAttachEvent>() {
                    @Override
                    public void accept(ViewAttachEvent viewAttachEvent) throws Exception {
                        AttachEvents(viewAttachEvent);
                    }
                });
        disposables.add(disposable);
    }

    /*监听焦点获取*/
    protected void RxFocusChanges(View view){
        Disposable disposable = RxView.focusChanges(view).skipInitialValue().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                focusChanges(aBoolean);
            }
        });
        disposables.add(disposable);
    }

    protected void RxDrags(View view){
        Disposable disposable = RxView.drags(view)
                .subscribe(new Consumer<DragEvent>() {
                    @Override
                    public void accept(DragEvent dragEvent) throws Exception {
                         Log.i("ccccccccccc","accept");
                    }
                });
        disposables.add(disposable);
     /*要触发 drag 需要下面的代码 开始 drag*/
//        final View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(ig_ic);
//        ig_ic.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
//                    ig_ic.startDragAndDrop(clipData,shadowBuilder,null,0);
//                } else {
//                    ig_ic.startDrag(clipData,shadowBuilder,null,0);
//                }
//                ig_ic.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
////                ig_ic.setVisibility(View.GONE);
//                return true;
//            }
//        });
    }

    /*监听按键 测试只有几个特殊的有反应 比如delete 但是删除效果 不执行*/
    protected void RxKeys(EditText view){
        Disposable disposable = RxView.keys(view)
                .subscribe(new Consumer<KeyEvent>() {
                    @Override
                    public void accept(KeyEvent keyEvent) throws Exception {
                    }
                });
        disposables.add(disposable);
    }

    /*简单监听 列表滑动*/
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void RxScrollChangeEvents(View view){
        Disposable disposable = RxView.scrollChangeEvents(view)
                .subscribe(new Consumer<ViewScrollChangeEvent>() {
                    @Override
                    public void accept(ViewScrollChangeEvent viewScrollChangeEvent) throws Exception {
                        scrollChangeEvents(viewScrollChangeEvent);
                    }
                });
        disposables.add(disposable);
    }

    @Override
    protected void onDestroy() {

        if(disposables.size()>0){
           for(Disposable disposable:disposables){
               disposable.dispose();
           }
        }
        super.onDestroy();
    }

    protected abstract void onClick(View view);

    protected abstract void onLongClick(View view);

    protected abstract void onHovers(MotionEvent motionEvent);

    protected  void AttachEvents(ViewAttachEvent viewAttachEvent){};

    protected void focusChanges(boolean focus){};

    protected void Drags(DragEvent dragEvent){}

    protected void Keys(KeyEvent keyEvent){}

    protected void scrollChangeEvents(ViewScrollChangeEvent viewScrollChangeEvent){}
}
