package gy.com.gycommon.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import gy.com.gycommon.constant.TransitionMode;
import gy.com.gycommon.permission.EasyPermissions;
import io.reactivex.functions.Consumer;

public abstract class BasePermissionActivity extends BaseActivity  {

    private RxPermissions rxPermissions;

    @Override
    protected void initRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
    }

    protected void requestEachPermission(String... permissions){
        rxPermissions.requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                    }
                });
    }

    protected void requestPermissions(String... permissions){
        rxPermissions.request(permissions)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                    }
                });
    }

    protected boolean isGranted(String permission){
        return rxPermissions.isGranted(permission);
    }

   protected boolean isRevoked(String permission){
        return rxPermissions.isRevoked(permission);
   }
}
