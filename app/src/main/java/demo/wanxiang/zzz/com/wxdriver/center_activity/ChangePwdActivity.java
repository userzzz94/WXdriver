package demo.wanxiang.zzz.com.wxdriver.center_activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.RegisterActivity;
import demo.wanxiang.zzz.com.wxdriver.bean.GetVerificationRegister_Bean;
import demo.wanxiang.zzz.com.wxdriver.bean.Register_Bean;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.TimeCount;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;
import demo.wanxiang.zzz.com.wxdriver.utils.VerificationUtil;

public class ChangePwdActivity extends BaseActivity {
    private ImageView back;

    private EditText repeatpwd, oldPwd, newPwd;
    private String repeatpwd_input, oldPwd_input, newPwd_input;
    private Button sure;

    private TimeCount mTimeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_change_pwd );
    }

    public void initData() {

    }

    public void initView() {
        back=findViewById( R.id.change_pwd_img_back );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        repeatpwd=findViewById( R.id.change_pwd_et_repeatpwd );
        oldPwd=findViewById( R.id.change_pwd_et_oldpwd );
        newPwd=findViewById( R.id.change_pwd_et_newpwd );

        sure=findViewById( R.id.change_pwd_btn_sure );
        sure.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handleInfo()) {
                    changePwd();
                }

            }
        } );

    }

    /**
     * 非空判断
     */
    public boolean handleInfo() {

        repeatpwd_input=repeatpwd.getText().toString().trim();
        oldPwd_input=oldPwd.getText().toString().trim();
        newPwd_input=newPwd.getText().toString().trim();

        if ("".equals( oldPwd_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入原登录密码" );
            return false;
        }
        if ("".equals( newPwd_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入新登录密码" );
            return false;
        }
        if ("".equals( repeatpwd_input )) {
            ToastUtils.getInstance( this ).showToast( "请重复输入新密码" );
            return false;
        }

        return true;
    }

    private void changePwd() {
        final ProgressDialog progressDialog=ProgressDialog.show( ChangePwdActivity.this, "", "请稍后······" );
        int id=Hawk.get( "userID" );
        OkGo. <String>post( UrlUtil.URL_Prefix + "api/changePassword" ).params( "userId", String.valueOf( id ) ).params( "oldPass", oldPwd_input ).params( "newPass", newPwd_input ).execute( new StringCallback() {
            @Override
            public void onSuccess(Response <String> response) {
                progressDialog.cancel();
                Gson gson=new Gson();
                Register_Bean bean=gson.fromJson( response.body(), Register_Bean.class );
                if (bean.isSuccess()) {
                    ToastUtils.getInstance( ChangePwdActivity.this ).showToast( "密码修改成功" );
                    finish();
                } else {
                    ToastUtils.getInstance( ChangePwdActivity.this ).showToast( bean.getMsg() );
                }
            }

            @Override
            public void onError(Response <String> response) {
                super.onError( response );
                progressDialog.cancel();
                ToastUtils.getInstance( ChangePwdActivity.this ).showToast( response.toString() );
                return;
            }
        } );

    }


}
