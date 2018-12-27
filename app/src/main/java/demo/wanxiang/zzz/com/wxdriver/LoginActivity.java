package demo.wanxiang.zzz.com.wxdriver;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import demo.wanxiang.zzz.com.wxdriver.bean.Login_Bean;
import demo.wanxiang.zzz.com.wxdriver.bean.VersionBean;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;

public class LoginActivity extends BaseActivity {
    //记录双击退出应用的第一次点击时间.
    private long firstTime=0;
    private static final String TAG="LoginActivity";

    private Button loginButton;

    private EditText account;
    private String account_input;
    private String password_input;
    private EditText password;
    private ImageView mIvLoginAccountDelete;
    private ImageView mIvLoginPasswordDelete;

    private CheckBox mCbLoginRememberAccount;
    private TextView regist;

    private LinearLayout parent;
    private PopupWindow popupWindow;

    private static final int REQUEST_PERMISSION_STORAGE = 0x01;
    private String mUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
    }

    public void initData() {
        requestNet_Version();
    }

    public void initView() {

        Hawk.init( this ).build();

        parent=findViewById( R.id.parent );
        mIvLoginAccountDelete=findViewById( R.id.ivLoginAccountDelete );
        mIvLoginAccountDelete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContent( account, mIvLoginAccountDelete );
            }
        } );
        mIvLoginPasswordDelete=findViewById( R.id.ivLoginPasswordDelete );
        mIvLoginPasswordDelete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContent( password, mIvLoginPasswordDelete );
            }
        } );

        mCbLoginRememberAccount=findViewById( R.id.activity_login_rememberAccount );

        account=(EditText) findViewById( R.id.activity_login_account );// 账号输入框
        account.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String account_input=account.getText().toString();
                if (!account_input.equals( "" )) {
                    mIvLoginAccountDelete.setVisibility( View.VISIBLE );
                } else {
                    mIvLoginAccountDelete.setVisibility( View.INVISIBLE );
                }
            }
        } );

        password=(EditText) findView( R.id.activity_login_password );//密码输入框
        password.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String password_input=password.getText().toString();
                if (!password_input.equals( "" )) {
                    mIvLoginPasswordDelete.setVisibility( View.VISIBLE );
                } else {
                    mIvLoginPasswordDelete.setVisibility( View.INVISIBLE );
                }
            }
        } );

        if (Hawk.contains( "remember" ) && (Boolean) Hawk.get( "remember" )) {

            if (Hawk.contains( "account" ) && Hawk.contains( "password" )) {
                account.setText( (String) Hawk.get( "account" ) );
                password.setText( (String) Hawk.get( "password" ) );
            }
            mCbLoginRememberAccount.setChecked( true );
        }

        regist=(TextView) findViewById( R.id.activity_login_register );
        regist.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register=new Intent( LoginActivity.this, RegisterActivity.class );
                startActivity( register );
            }
        } );
        loginButton=(Button) findViewById( R.id.activity_login_loginbutton );// 登录按钮
        loginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //收集登录的信息并进行非空判断
                if (handleLoginInfo()) {

                    //showPopupWindow();
                    requestNet_login();

                }
            }
        } );

    }

    //删除文本框内容
    private void deleteContent(EditText editText, ImageView imageView) {
        editText.setText( "" );
        imageView.setVisibility( View.INVISIBLE );
        editText.setFocusable( true );
        editText.requestFocus();
    }

    /**
     * 处理登录信息, 主要进行非空判断
     */
    public boolean handleLoginInfo() {

        account_input=account.getText().toString().trim();
        password_input=password.getText().toString().trim();

        if ("".equals( account_input )) {// 非空判断手机号
            ToastUtils.getInstance( this ).showToast( "请输入用户名" );
            return false;
        }

        if ("".equals( password_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入登录密码" );
            return false;
        }

        return true;
    }

    private void requestNet_login() {
        final ProgressDialog progressDialog=ProgressDialog.show( LoginActivity.this, "", "请稍后······" );
        Map <String, Object> params=new HashMap <>();
        params.put( "loginname", account_input );
        params.put( "password", password_input );
        JSONObject jsonObject=new JSONObject( params );

        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/driverLogin" ).tag( this ).upJson( jsonObject ).execute( new StringCallback() {
            @Override
            public void onSuccess(Response <String> response) {
                Log.d( "yyyyyy", "onSuccess" );
                progressDialog.cancel();
                Gson gson=new Gson();
                Login_Bean driver=gson.fromJson( response.body(), Login_Bean.class );

                if (driver.isSuccess()) {// 登陆成功
                    Hawk.put( "remember", mCbLoginRememberAccount.isChecked() );
                    Hawk.put( "account", driver.getData().getLoginname() );
                    Hawk.put( "password", driver.getData().getPassword() );
                    Hawk.put( "userID", driver.getData().getId() );
                    Hawk.put( "Licenseplate",driver.getData().getLicenseplate() ) ;
                    if (driver.getData().getAuditstate() == 1 || driver.getData().getAuditstate() == 20) {
                        Intent personalInformation=new Intent( LoginActivity.this, ProveRegisterInfoActivity.class );
                        startActivity( personalInformation );
                    } else if (driver.getData().getAuditstate() == 10) {
                        Intent login=new Intent( LoginActivity.this, MainActivity.class );
                        startActivity( login );
                        finish();
                    } else if (driver.getData().getAuditstate() == 5) {
                        AlertDialog.Builder builder=new AlertDialog.Builder( LoginActivity.this );
                        builder.setTitle( "提示" ).setMessage( "个人资料审核中，请审核通过后再次登录！" ).setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        } ).create().show();
                    }
                } else {
                    ToastUtils.getInstance( LoginActivity.this ).showToast( driver.getMsg() );
                    //popupWindow.dismiss();
                }

            }

            @Override
            public void onError(Response <String> response) {
                super.onError( response );
                Log.d( "yyyyyy", "onError" );
                ToastUtils.getInstance( LoginActivity.this ).showToast( "登录失败" );
                //popupWindow.dismiss();

            }
        } );

    }

    /**
     * 获取当前app的版本号
     * @return
     */
    private int getCurrentVersion(){
        int mVersionCode = 0;
        try {
            PackageManager mPackageManager = this.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(this.getPackageName(),0);
            mVersionCode = mPackageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return mVersionCode;
    }

    /*
获取服务器版本号
 */
    private void requestNet_Version(){
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this,"","请稍后······");
        OkGo.<String>post(UrlUtil.URL_Prefix + "api/updatedVersion?type=S&oldVersion=" + getCurrentVersion())
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.dismiss();
                        Gson gson = new Gson();
                        final VersionBean versionBean = gson.fromJson(response.body(),VersionBean.class);
                        if(versionBean.isSuccess() && versionBean.getData() != null){
                            mUrl = versionBean.getData().getUrl();
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            TextView tv = new TextView(LoginActivity.this);
                            tv.setText("版本升级");
                            tv.setTextSize(20);
                            tv.setTextColor( Color.parseColor("#ff4081"));
                            tv.setPadding(70, 60, 20, 10);
                            builder.setCustomTitle(tv)
                                    .setCancelable(false)
                                    .setMessage("检测到新版本 V" + versionBean.getData().getName() + "\n" + versionBean.getData().getRemark())
                                    .setNegativeButton("浏览器内更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Uri uri = Uri.parse(UrlUtil.URL_Prefix_UPDATE_URL+ mUrl);
                                            Intent it = new Intent(Intent.ACTION_VIEW,uri);
                                            startActivity(it);
                                        }
                                    })
                                    .setPositiveButton("应用内更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if(ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                                                ActivityCompat.requestPermissions(LoginActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION_STORAGE);
                                            }else{
                                                downloadAPK(mUrl);
                                            }
                                        }
                                    }).create().show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadAPK(mUrl);
            } else {
                Toast.makeText(LoginActivity.this,"权限被禁止，无法下载文件！",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
	下载安装apk
	 */
    private void downloadAPK(String url){
        final File file = new File("sdcard/download/WanXiangDriver.apk");//如果存在已下载apk，则先删除
        if(file.exists()){
            file.delete();
        }
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        OkGo.<File>get(UrlUtil.URL_Prefix_UPDATE_URL + url)
                .tag(this)
                .execute(new FileCallback() {
                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        progressDialog.setCancelable(false);
                        progressDialog.setMessage("正在下载最新版本，请不要关闭程序");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.setMax(100);//设置进度条的最大值
                        progressDialog.setProgressNumberFormat(null);
                        progressDialog.show();
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        progressDialog.setProgress((int)(progress.fraction * 100));
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        Toast.makeText(LoginActivity.this,"下载成功",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        if(Build.VERSION.SDK_INT >= 24) {//判读版本是否在7.0以上
                            File filePath = new File( Environment.getExternalStorageDirectory(), "download");
                            File newFile = new File(filePath, "WanXiangDriver.apk");
                            Uri contentUri = FileProvider.getUriForFile(LoginActivity.this, "demo.wanxiang.zzz.com.wxdriver.fileprovider", newFile);
                            Intent install = new Intent(Intent.ACTION_VIEW);
                            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            install.setDataAndType(contentUri, "application/vnd.android.package-archive");
                            startActivity(install);
                        } else{
                            Intent intent = new Intent(Intent.ACTION_VIEW);//安装app
                            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this,"下载失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void showPopupWindow() {
        View view1=View.inflate( LoginActivity.this, R.layout.popupwindow_login, null );

        popupWindow=new PopupWindow();
        popupWindow.setContentView( view1 );
        popupWindow.setWidth( ViewGroup.LayoutParams.MATCH_PARENT );
        popupWindow.setHeight( ViewGroup.LayoutParams.MATCH_PARENT );

        popupWindow.setFocusable( false );
        popupWindow.setTouchable( true );
        popupWindow.setOutsideTouchable( false );// 点击popupwindow外边能够取消显示popupwindow

        popupWindow.showAtLocation( parent, Gravity.CENTER, 0, 0 );
    }

    //双击退出
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondTime=System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                ToastUtils.getInstance( LoginActivity.this ).showToast( "再按一次退出程序" );
                firstTime=secondTime;
                return true;
            } else {
                finish();
            }
        }

        return super.onKeyUp( keyCode, event );
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (popupWindow != null && popupWindow.isShowing()) {
//            popupWindow.dismiss();
//            popupWindow=null;
//        }
//    }


}
