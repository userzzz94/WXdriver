package demo.wanxiang.zzz.com.wxdriver.center_activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.wanxiang.zzz.com.wxdriver.LoginActivity;
import demo.wanxiang.zzz.com.wxdriver.ProveRegisterInfoActivity;
import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.bean.ChangeCarInfoBean;
import demo.wanxiang.zzz.com.wxdriver.bean.RegistInfoBean;
import demo.wanxiang.zzz.com.wxdriver.bean.UploadImg_Bean;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;
import demo.wanxiang.zzz.com.wxdriver.utils.VerificationUtil;
import demo.wanxiang.zzz.com.wxdriver.view.MyEditText;

public class ChangeCarInfoActivity extends BaseActivity {
    private ImageView driverImg;
    private ImageView lisenceImg;
    private ImageView cardImg;
    private ImageView cardbackImg;

    private String mA1 = null;
    private String mB1 = null;
    private String mC1 = null;
    private String mC2 = null;
    private int mWhichImg;

    private MyEditText cartel, carnumber;
    private String cartel_input, carnumber_input;

    private Button change;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_change_car_info );
    }
    public void initData() {

    }

    public void initView() {
        cartel=findViewById( R.id.et_ChangeCar_cartel );
        carnumber=findViewById( R.id.et_ChangeCar_carnum );

        back=findViewById( R.id.activity_ChangeCar_back );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        } );

        lisenceImg=findViewById( R.id.img_ChangeCar_lisence_card );
        lisenceImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPictureSelector( PictureSelector_RequestCodeConfig.REGISTER_LISENCEPHOTO );
                mWhichImg = 0;
                if(ContextCompat.checkSelfPermission(ChangeCarInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ChangeCarInfoActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    PictureSelector.create(ChangeCarInfoActivity.this)
                            .openGallery( PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .forResult( PictureConfig.CHOOSE_REQUEST);
                }
            }
        } );

        driverImg=findViewById( R.id.img_ChangeCar_driver_card );
        driverImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPictureSelector( PictureSelector_RequestCodeConfig.REGISTER_DRIVECARD );
                mWhichImg = 1;
                if(ContextCompat.checkSelfPermission(ChangeCarInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ChangeCarInfoActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    PictureSelector.create(ChangeCarInfoActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            }
        } );

        cardImg=findViewById( R.id.img_ChangeCar_idcard_one );
        cardImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWhichImg = 2;
                if(ContextCompat.checkSelfPermission(ChangeCarInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ChangeCarInfoActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    PictureSelector.create(ChangeCarInfoActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            }
        } );

        cardbackImg=findViewById( R.id.img_ChangeCar_idcard_two );
        cardbackImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPictureSelector( PictureSelector_RequestCodeConfig.REGISTER_IDCARD_BACK );
                mWhichImg = 3;
                if(ContextCompat.checkSelfPermission(ChangeCarInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ChangeCarInfoActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    PictureSelector.create(ChangeCarInfoActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            }
        } );
        change=findViewById( R.id.btnChangeCarInfo_finish );
        change.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handleInfo()) {

                    requestNet_Change();
                }
            }
        } );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PictureSelector.create( ChangeCarInfoActivity.this ).openGallery( PictureMimeType.ofImage() ).maxSelectNum( 1 )// 最大图片选择数量 int
                            .forResult( PictureConfig.CHOOSE_REQUEST );
                } else {
                    Toast.makeText( ChangeCarInfoActivity.this, "需要同意权限", Toast.LENGTH_SHORT ).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PictureConfig.CHOOSE_REQUEST:
                if(resultCode == RESULT_OK){
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    File file = new File(selectList.get(0).getPath());
                    images(file,mWhichImg);
                }
                break;
        }
    }

    private void images(final File file, final int mWhichImg) {
        final ProgressDialog progressDialog=ProgressDialog.show( ChangeCarInfoActivity.this, "", "请稍后······" );
        OkGo. <String>post( UrlUtil.URL_Prefix + "api/uploadFile" )
                .tag( this )
                .params("file",file)
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        UploadImg_Bean bean=gson.fromJson( response.body(), UploadImg_Bean.class );
                        if (bean.isSuccess()) {
                            if(mWhichImg == 0){ //驾驶证
                                mA1 = bean.getData().getUrl();
                                Glide.with(ChangeCarInfoActivity.this).load(file).into(lisenceImg);
                            }else if(mWhichImg == 1){ //行驶证
                                mB1 = bean.getData().getUrl();
                                Glide.with(ChangeCarInfoActivity.this).load(file).into(driverImg);
                            }else if(mWhichImg == 2){ //身份证
                                mC1 = bean.getData().getUrl();
                                Glide.with(ChangeCarInfoActivity.this).load(file).into(cardImg);
                            }else if(mWhichImg == 3){ //身份证反面
                                mC2 = bean.getData().getUrl();
                                Glide.with(ChangeCarInfoActivity.this).load(file).into(cardbackImg);
                            }
                        } else {
                            ToastUtils.getInstance( ChangeCarInfoActivity.this ).showToast( bean.getMsg() );
                        }
                    }

                    @Override
                    public void onError(Response <String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        Toast.makeText( ChangeCarInfoActivity.this, response.toString(), Toast.LENGTH_SHORT ).show();
                    }
                } );
    }

    private boolean handleInfo() {
        cartel_input=cartel.getText().toString().trim();
        carnumber_input=carnumber.getText().toString().trim();

        if ("".equals( cartel_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入随车电话" );
            return false;
        }
        if ("".equals( carnumber_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入车牌号" );
            return false;
        }

        if (mA1 == null) {
            ToastUtils.getInstance( this ).showToast( "请上传驾驶证图片" );
            return false;
        }
        if (mB1 == null) {
            ToastUtils.getInstance( this ).showToast( "请上传行驶证图片" );
            return false;
        }
        if (mC1 == null) {
            ToastUtils.getInstance( this ).showToast( "请上传身份证图片" );
            return false;
        }
        if (mC2 == null) {
            ToastUtils.getInstance( this ).showToast( "请上传身份证背面图片" );
            return false;
        }
        return true;
    }

    private void requestNet_Change() {
        Map filePath = new HashMap<>();
        filePath.put("A1",mA1);
        filePath.put("B1",mB1);
        filePath.put("C1",mC1);
        filePath.put("C2",mC2);
        JSONObject jsonObject = new JSONObject(filePath);
        Log.e( "eeee", jsonObject.toString());

//        Map<String,Object> params = new HashMap<>();
//        params.put("userId", Hawk.get("userID"));
//        params.put("licenseplate", carnumber_input );
//        params.put("accessoryphone",cartel_input);
//        params.put("filepath",filePath );
//        JSONObject jsonObject = new JSONObject(params);

        final ProgressDialog progressDialog = ProgressDialog.show(ChangeCarInfoActivity.this,"","请稍后······");
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/modifyLicenseplate")
                .tag( this )
        .params("userId", (int)Hawk.get("userID"))
        .params( "licenseplate", carnumber_input )
        .params("accessoryphone",cartel_input)
        .params("filepath",jsonObject.toString())
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response <String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        ChangeCarInfoBean result=gson.fromJson( response.body(), ChangeCarInfoBean.class );
                        if (result.isSuccess()) {// 信息更改成功
                            AlertDialog.Builder builder = new AlertDialog.Builder(ChangeCarInfoActivity.this);
                            builder.setTitle("提示")
                                    .setMessage("车辆信息更改成功，等待审核中！请退出后重新登录！")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            Intent intent_quit=new Intent(ChangeCarInfoActivity.this, LoginActivity.class);
                                            startActivity( intent_quit );
                                            finish();
                                        }
                                    }).create().show();
                        } else {
                            ToastUtils.getInstance( ChangeCarInfoActivity.this ).showToast( result.getMsg() );
                        }

                    }

                    @Override
                    public void onError(Response <String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        ToastUtils.getInstance( ChangeCarInfoActivity.this ).showToast( response.toString() );
                    }
                } );
    }

}
