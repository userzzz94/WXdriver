package demo.wanxiang.zzz.com.wxdriver;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import demo.wanxiang.zzz.com.wxdriver.bean.GetVerificationRegister_Bean;
import demo.wanxiang.zzz.com.wxdriver.bean.Register_Bean;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.TimeCount;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;
import demo.wanxiang.zzz.com.wxdriver.utils.VerificationUtil;

public class RegisterActivity extends BaseActivity {
    private EditText tel,password,code;
    private Button register;
    private Button getCode;
    private String tel_input,password_input,code_input;

    private ImageView back;

    private TimeCount mTimeCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
    }

    public void initData() {

    }

    public void initView() {

        Hawk.init( this ).build();
        tel= findViewById(R.id.register_et_telnumber);
        password= findViewById(R.id.register_et_password);
        code = findViewById(R.id.register_et_send_code);

        back=findViewById( R.id.register_title_back );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        register = findViewById(R.id.btnRegister);
        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(handleRegistInfo()) {
                    registerFromNet();
                }

            }
        });
        
        //获取验证码
        getCode=findViewById( R.id.register_btn_send_code ) ;
        getCode.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCodeFromNet();
            }
        });

    }

    private void getCodeFromNet(){

        if(!getCode.isEnabled()){ //如果按钮不可用 就不执行此逻辑.
            return;
        }
        String phone = tel.getText().toString().trim();
        if(!VerificationUtil.judgePhoneNums(phone)){
            ToastUtils.getInstance(RegisterActivity.this).showToast("手机号填写有误");
            return;
        }

        getCode.setEnabled(false);
        mTimeCount = new TimeCount(60000,1000,getCode);
        mTimeCount.start();

        OkGo.<String>post( UrlUtil.URL_Prefix+"api/sendSmCode?phoneNum="+phone)
                .tag( this )
                .execute(new StringCallback(){
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        GetVerificationRegister_Bean bean = gson.fromJson(response.body(), GetVerificationRegister_Bean.class);
                        if(!bean.isSuccess()){
                            mTimeCount.cancel();
                            mTimeCount.onFinish();
                            ToastUtils.getInstance(RegisterActivity.this).showToast(bean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mTimeCount.cancel();
                        mTimeCount.onFinish();
                        ToastUtils.getInstance(RegisterActivity.this).showToast("网络不稳定,获取验证码失败");
                        return;
                    }
                });
    }
    /**
     * 非空判断
     */
    public boolean handleRegistInfo() {

        tel_input =tel.getText().toString().trim();
        password_input=password.getText().toString().trim();
        code_input=code.getText().toString().trim();

        if ("".equals( tel_input )) {// 非空判断手机号
            ToastUtils.getInstance( this ).showToast( "请输入手机号" );
            return false;
        } else {
            if (!VerificationUtil.judgePhoneNums( tel_input )) {
                ToastUtils.getInstance( RegisterActivity.this ).showToast( "请输入正确的手机号" );
                return false;
            }
        }

        if ("".equals( password_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入登录密码" );
            return false;
        }
        //        else {
        //            if (VerificationUtil.judgePassword( password_input ) == -1) {
        //                ToastUtils.getInstance( RegistMasterActivity.this ).showToast( "密码中间不允许有空格" );
        //                return false;
        //            } else if (VerificationUtil.judgePassword( password_input ) == -2) {
        //                ToastUtils.getInstance( RegistMasterActivity.this ).showToast( "密码中间不允许有汉字" );
        //                return false;
        //            } else if (VerificationUtil.judgePassword( password_input ) == -3) {
        //                ToastUtils.getInstance( RegistMasterActivity.this ).showToast( "密码中不允许有除字母、数字外的特殊符号" );
        //                return false;
        //            } else if (VerificationUtil.judgePassword( password_input ) == -4) {
        //                ToastUtils.getInstance( RegistMasterActivity.this ).showToast( "密码不能全是数字" );
        //                return false;
        //            }
        //
        //        }

        if("".equals( code_input )){
            ToastUtils.getInstance( this ).showToast( "请输入验证码" );
            return false;
        }else {
            if (!VerificationUtil.judgeVerifyCode( code_input )) {
                ToastUtils.getInstance( RegisterActivity.this ).showToast( "请输入正确的验证码" );
                return false;
            }
        }

        return true;
    }


    //点击注册按钮接口
    private void registerFromNet() {
        final ProgressDialog progressDialog = ProgressDialog.show(RegisterActivity.this,"","请稍后······");
        Map<String,Object> params=new HashMap <>( ) ;
        params.put( "loginname", tel_input );
        params.put( "password", password_input );
        params.put( "verification", code_input );
        JSONObject jsonObject=new JSONObject( params );
        OkGo.<String>post( UrlUtil.URL_Prefix+"driverapi/register")
                .tag( this )
                .upJson( jsonObject )
                .execute(new StringCallback(){
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.cancel();
                        Gson gson = new Gson();
                        Register_Bean bean = gson.fromJson(response.body(), Register_Bean.class);
                        if(bean.isSuccess()){
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setTitle("提示")
                                    .setMessage("注册成功，去登录！")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    }).create().show();
//
                        }else  {
                            ToastUtils.getInstance(RegisterActivity.this).showToast(bean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        progressDialog.cancel();
                        ToastUtils.getInstance(RegisterActivity.this).showToast("网络不稳定,注册失败");
                        return;
                    }
                });
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimeCount != null) {
            mTimeCount.cancel();
            mTimeCount.onFinish();
        }
    }


}
