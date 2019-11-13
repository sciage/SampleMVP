package com.example.sampleapp.rx;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.sampleapp.R;
import com.example.sampleapp.di.component.ActivityComponent;
import com.example.sampleapp.di.component.DaggerActivityComponent;
import com.example.sampleapp.di.module.ActivityModule;
import com.google.android.material.snackbar.Snackbar;

import butterknife.Unbinder;
import io.fabric.sdk.android.services.network.NetworkUtils;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {
    protected SampleApplication application;
    protected Toolbar toolbar;
    protected SharedPreferences preferences;

    private Unbinder mUnBinder;
  //  private ProgressDialog mProgressDialog;

    public ActivityComponent mActivityComponent;
    public boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        application = (SampleApplication) getApplication();

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((SampleApplication) getApplication()).getComponent())
                .build();

    }


    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;

    //    scheduler.onResume();
    }



    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
   //     scheduler.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    @Override
    public void finish() {
        super.finish();

    }



    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        super.setContentView(layoutResId);

        toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }


    public Toolbar getToolbar() {
        return toolbar;
    }

    public SampleApplication getVoicemeApplication() {
        return application;
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

  /*  @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }  */

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading() {
    //    hideLoading();
    //    mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
      //  if (mProgressDialog != null && mProgressDialog.isShowing()) {
      //      mProgressDialog.cancel();
      //  }
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        snackbar.show();
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return true;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
      //  startActivity(StartPage01GetStarted.getStartIntent(this));
       // finish();
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    protected abstract void setUp();


    @Override
    public void onBackPressed(){
            super.onBackPressed();
    }
}
