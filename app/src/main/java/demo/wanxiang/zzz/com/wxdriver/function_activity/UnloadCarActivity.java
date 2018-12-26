package demo.wanxiang.zzz.com.wxdriver.function_activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import demo.wanxiang.zzz.com.wxdriver.ProveRegisterInfoActivity;
import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.bean.DrivergetOrder_Bean;
import demo.wanxiang.zzz.com.wxdriver.bean.UploadImg_Bean;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;
import demo.wanxiang.zzz.com.wxdriver.utils.VerificationUtil;

public class UnloadCarActivity extends BaseActivity {
    private ImageView back;
    private ImageView photo;
    private String mPounds = null;
    private int mImg;

    private EditText weight,weightPi;
    private String weight_input,weightPi_input;
    private Button sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_unload_car );
    }

    public void initData() {

    }

    public void initView() {

        Hawk.init( this ).build();
        back=findViewById( R.id.ivUnloadCarBack );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        weight=findViewById(R.id.unload_car_pure_weight  );
        weightPi=findViewById( R.id.unload_car_pi_weight );

        photo=findViewById( R.id.unload_car_pounds_photo );
        photo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImg=1;
                if(ContextCompat.checkSelfPermission(UnloadCarActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(UnloadCarActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    PictureSelector.create(UnloadCarActivity.this)
                            .openGallery( PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .forResult( PictureConfig.CHOOSE_REQUEST);
                }
            }
        } );

        sure=findViewById( R.id.unload_car_sureBtn );
        sure.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(handleInfo()) {
                    unloadCarSure();
                }
            }
        } );
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PictureSelector.create( UnloadCarActivity.this ).openGallery( PictureMimeType.ofImage() ).maxSelectNum( 1 )// 最大图片选择数量 int
                            .forResult( PictureConfig.CHOOSE_REQUEST );
                } else {
                    Toast.makeText( UnloadCarActivity.this, "需要同意权限", Toast.LENGTH_SHORT ).show();
                }
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PictureConfig.CHOOSE_REQUEST:
                if(resultCode == RESULT_OK){
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    File file = new File(selectList.get(0).getPath());
                    images(file,mImg);
                }
                break;
        }
    }

    private void images(final File file, final int mWhichImg) {
        final ProgressDialog progressDialog=ProgressDialog.show( UnloadCarActivity.this, "", "请稍后······" );
        OkGo. <String>post( UrlUtil.URL_Prefix + "api/uploadFile" )
                .tag( this )
                .params("file",file)
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response <String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        UploadImg_Bean bean=gson.fromJson( response.body(), UploadImg_Bean.class );
                        if (bean.isSuccess()) {
                          mPounds=bean.getData().getUrl();
                          Glide.with( UnloadCarActivity.this ).load( file ).into( photo);

                        } else {
                            ToastUtils.getInstance( UnloadCarActivity.this ).showToast( bean.getMsg() );
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        Toast.makeText( UnloadCarActivity.this, response.toString(), Toast.LENGTH_SHORT ).show();
                    }
                } );
    }

    private boolean handleInfo() {

        weight_input=weight.getText().toString().trim();
        weightPi_input=weightPi.getText().toString().trim();

        if ("".equals( weight_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入卸车重量" );
            return false;
        }
        if ("".equals( weightPi_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入车辆皮重" );
            return false;
        }

        if (mPounds == null) {
            ToastUtils.getInstance( this ).showToast( "请上传榜单照片" );
            return false;
        }

        return true;
    }

    //卸车确认
    private void  unloadCarSure() {
        final ProgressDialog progressDialog=ProgressDialog.show( UnloadCarActivity.this, "", "请稍后······" );
        Map filePath = new HashMap<>();
        filePath.put("Pounds",mPounds);
//        weight_input=weight.getText().toString().trim();
//        weightPi_input=weightPi.getText().toString().trim();
//        Map<String,Object> params = new HashMap<>();
//        params.put( "dispatchId", Hawk.get( "dispatchId") );
//        params.put( "fileurl",filePath );
//        params.put( "grossweight",weight_input );
//        params.put("userId", Hawk.get( "userID" ) ) ;
//        params.put("userName", Hawk.get("userName")) ;
//        params.put("weight",weightPi_input) ;
//        JSONObject jsonObject = new JSONObject(params);
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/unloadingConfirmation" )
                .params( "dispatchId",String.valueOf( Hawk.get( "dispatchId"))  )
                .params( "fileurl", mPounds )
                .params( "grossweight",weight_input )
                .params("userId",String.valueOf( Hawk.get( "userID" ) ))
                .params("userName",String.valueOf( Hawk.get("userName")))
                .params("weight",weightPi_input)
//                .tag( this )
//                .upJson( jsonObject )
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        DrivergetOrder_Bean bean=gson.fromJson( response.body(), DrivergetOrder_Bean.class );

                        if(bean.isSuccess()){
                            finish();

                        }else{
                            ToastUtils.getInstance(UnloadCarActivity.this).showToast(bean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response <String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        ToastUtils.getInstance(UnloadCarActivity.this ).showToast( response.toString() );
                    }
                } );
    }


}
