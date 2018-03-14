package com.android.yongnongpda.activity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.yongnongpda.MainActivity;
import com.android.yongnongpda.R;
import com.android.yongnongpda.application.MyApp;
import com.android.yongnongpda.appupdate.APKUpdate;
import com.android.yongnongpda.confing.AppConfig;
import com.android.yongnongpda.utils.AssistHelper;
import com.android.yongnongpda.utils.OkHttpHelper;
import com.winsafe.mylibrary.application.AppMgr;
import com.winsafe.mylibrary.utils.CommonHelper;
import com.winsafe.mylibrary.utils.NetworkHelper;
import com.winsafe.mylibrary.utils.StringHelper;
import com.winsafe.mylibrary.view.AppBaseActivity;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * Created by shijie.yang on 2017/7/26.
 */

public class LoginActivity extends AppBaseActivity {
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.cbRemberPassword)
    CheckBox cbRemberPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvImei)
    TextView tvImei;
    @BindView(R.id.tvVersion)
    TextView tvVersion;
    @BindView(R.id.btnSetting)
    Button btnSetting;
    @BindView(R.id.etUrl)
    EditText etUrl;
    @BindView(R.id.btnSure)
    Button btnSure;
    @BindView(R.id.showUrl)
    RelativeLayout showUrl;
    private boolean isSetUrl;
    private String user = "", passwd = "";
    private long mExitTime;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case AppConfig.HANDLER_MESSAGE:
                    MyApp.shared.saveValueByKey(AppConfig.USERNAME, user);
                    MyApp.shared.saveValueByKey(AppConfig.PASSWORD, passwd);
                    if (cbRemberPassword.isChecked()) {
                        MyApp.shared.saveValueByKey(AppConfig.ISSAVE_PASSWORD, "true");
                    } else {
                        MyApp.shared.saveValueByKey(AppConfig.ISSAVE_PASSWORD, "false");
                    }
                    openActivity(LoginActivity.this, MainActivity.class, false);
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected int activityLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
         /* 如果SDK版本大于等于23、或者Android系统版本是6.0以上，那么需要动态请求创建文件的权限 */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        etUrl.setText(AppConfig.URL_BASE);
        tvImei.setText(String.format("IMEI:%s", CommonHelper.getIMEI(this)));
        tvVersion.setText(String.format("Version:%s", CommonHelper.getAppVersionName(this)));
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        etUserName.setText(AssistHelper.getUserName());
        if (AssistHelper.isSavePassword().equals("true")) {
            etPassword.setText(AssistHelper.getPassword());
            cbRemberPassword.setChecked(true);
        }

        //自动检测更新
        if (NetworkHelper.isNetworkAvailable(this) == true) {
            APKUpdate.checkAPKVersion(this, false);
        }
    }

    @OnClick({R.id.btnLogin})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnLogin:
                // openActivity(LoginActivity.this, MainActivity.class, false);

                user = etUserName.getText().toString().trim();
                passwd = etPassword.getText().toString().trim();
                if (StringHelper.isNullOrEmpty(user)) {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringHelper.isNullOrEmpty(passwd)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put(AppConfig.USERNAME, user);
                map.put(AppConfig.PASSWORD, passwd);
                OkHttpHelper.post(LoginActivity.this, AppConfig.URL_LOGIN, map, handler, "登录中···");
                break;

            default:
                break;

        }
    }

    //返回退出程序
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, AssistHelper.showString(this, R.string.toast_press_again_quit), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                AppMgr.getInstance().quit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /* 根据获取的授权创建下载文件夹 */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //创建APP更新时需要的文件夹
                    String sdRoot = CommonHelper.getExternalStoragePath();
                    if (!sdRoot.equals("")) {
                        String downloadApkPath = sdRoot + "/" + CommonHelper.getAppPackageName(this);
                        File newFilePath = new File(downloadApkPath);
                        if (!newFilePath.exists()) {
                            newFilePath.mkdirs();
                        }
                    }
                }
                break;
        }
    }

}
