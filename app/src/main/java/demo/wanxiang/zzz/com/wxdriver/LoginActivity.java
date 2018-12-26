package demo.wanxiang.zzz.com.wxdriver;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import demo.wanxiang.zzz.com.wxdriver.bean.Login_Bean;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
    }

    public void initData() {

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
