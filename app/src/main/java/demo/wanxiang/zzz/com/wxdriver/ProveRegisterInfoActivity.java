package demo.wanxiang.zzz.com.wxdriver;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import com.luck.picture.lib.tools.PictureFileUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.wanxiang.zzz.com.wxdriver.bean.GetVerificationRegister_Bean;
import demo.wanxiang.zzz.com.wxdriver.bean.RegistInfoBean;
import demo.wanxiang.zzz.com.wxdriver.bean.UploadImg_Bean;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.PictureSelector_RequestCodeConfig;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;
import demo.wanxiang.zzz.com.wxdriver.utils.VerificationUtil;
import demo.wanxiang.zzz.com.wxdriver.view.MyEditText;

public class ProveRegisterInfoActivity extends BaseActivity {
    private ImageView driverImg;
    private ImageView lisenceImg;
    private ImageView cardImg;
    private ImageView cardbackImg;

    private String mA1 = null;
    private String mB1 = null;
    private String mC1 = null;
    private String mC2 = null;
    private int mWhichImg;

    private MyEditText name, address, idnumber, cartel, carnumber;
    private String name_input, address_input, idnumber_input, cartel_input, carnumber_input;

    private Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_prove_register_info );
    }

    public void initData() {

    }

    public void initView() {

        Hawk.init( this ).build();
        name=findViewById( R.id.et_register_name );
        address=findViewById( R.id.et_register_address );
        idnumber=findViewById( R.id.et_register_idcard );
        cartel=findViewById( R.id.et_register_cartel );
        carnumber=findViewById( R.id.et_register_carnum );

        lisenceImg=findViewById( R.id.img_lisence_card );
        lisenceImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPictureSelector( PictureSelector_RequestCodeConfig.REGISTER_LISENCEPHOTO );
                mWhichImg = 0;
                if(ContextCompat.checkSelfPermission(ProveRegisterInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ProveRegisterInfoActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    PictureSelector.create(ProveRegisterInfoActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            }
        } );

        driverImg=findViewById( R.id.img_driver_card );
        driverImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPictureSelector( PictureSelector_RequestCodeConfig.REGISTER_DRIVECARD );
                mWhichImg = 1;
                if(ContextCompat.checkSelfPermission(ProveRegisterInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ProveRegisterInfoActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    PictureSelector.create(ProveRegisterInfoActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            }
        } );

        cardImg=findViewById( R.id.img_idcard_one );
        cardImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWhichImg = 2;
                if(ContextCompat.checkSelfPermission(ProveRegisterInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ProveRegisterInfoActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    PictureSelector.create(ProveRegisterInfoActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            }
        } );

        cardbackImg=findViewById( R.id.img_idcard_two );
        cardbackImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPictureSelector( PictureSelector_RequestCodeConfig.REGISTER_IDCARD_BACK );
                mWhichImg = 3;
                if(ContextCompat.checkSelfPermission(ProveRegisterInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ProveRegisterInfoActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    PictureSelector.create(ProveRegisterInfoActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            }
        } );
        regist=findViewById( R.id.btnRegistInfo_finish );
        regist.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handleMasterInfo()) {

                    requestNet_Regist();
                }
            }
        } );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onActivityResult( requestCode, requestCode, data );
                switch (requestCode) {
                    case 1:
                        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                            PictureSelector.create(ProveRegisterInfoActivity.this)
                                    .openGallery(PictureMimeType.ofImage())
                                    .maxSelectNum(1)// 最大图片选择数量 int
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }else{
                            Toast.makeText(ProveRegisterInfoActivity.this,"需要同意权限",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

//        if (resultCode == RESULT_OK) {
//            List <LocalMedia> images=PictureSelector.obtainMultipleResult( data );
//            String image_path=images.get( 0 ).getCompressPath();
//            switch (requestCode) {
//                case PictureSelector_RequestCodeConfig.REGISTER_LISENCEPHOTO:
//                    addressMap.put( "A1", image_path );
//                    photoRecord[0]=1;
//                    break;
//                case PictureSelector_RequestCodeConfig.REGISTER_DRIVECARD:
//                    addressMap.put( "B1", image_path );
//                    photoRecord[1]=1;
//                    break;
//                case PictureSelector_RequestCodeConfig.REGISTER_IDCARD:
//                    addressMap.put( "C1", image_path );
//                    photoRecord[2]=1;
//                    break;
//                case PictureSelector_RequestCodeConfig.REGISTER_IDCARD_BACK:
//                    addressMap.put( "C2", image_path );
//                    photoRecord[3]=1;
//                    break;
//
//            }
//            List <LocalMedia> selectList=PictureSelector.obtainMultipleResult( data );
//            List <File> photoFiles=new ArrayList <File>();
//            for (int i=0; i < selectList.size(); i++) {
//                File file=new File( selectList.get( i ).getPath() );
//                photoFiles.add( file );
//            }
//            images( photoFiles );
//            super.onActivityResult( requestCode, resultCode, data );
//        }
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
        final ProgressDialog progressDialog=ProgressDialog.show( ProveRegisterInfoActivity.this, "", "请稍后······" );
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
                    if(mWhichImg == 0){ //驾驶证
                        mA1 = bean.getData().getUrl();
                        Glide.with(ProveRegisterInfoActivity.this).load(file).into(lisenceImg);
                    }else if(mWhichImg == 1){ //行驶证
                        mB1 = bean.getData().getUrl();
                        Glide.with(ProveRegisterInfoActivity.this).load(file).into(driverImg);
                    }else if(mWhichImg == 2){ //身份证
                        mC1 = bean.getData().getUrl();
                        Glide.with(ProveRegisterInfoActivity.this).load(file).into(cardImg);
                    }else if(mWhichImg == 3){ //身份证反面
                        mC2 = bean.getData().getUrl();
                        Glide.with(ProveRegisterInfoActivity.this).load(file).into(cardbackImg);
                    }
                } else {
                    ToastUtils.getInstance( ProveRegisterInfoActivity.this ).showToast( bean.getMsg() );
                }
            }

            @Override
            public void onError(Response <String> response) {
                super.onError( response );
                progressDialog.cancel();
                Toast.makeText( ProveRegisterInfoActivity.this, response.toString(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private boolean handleMasterInfo() {

        name_input=name.getText().toString().trim();
        cartel_input=cartel.getText().toString().trim();
        carnumber_input=carnumber.getText().toString().trim();
        address_input=address.getText().toString().trim();
        idnumber_input=idnumber.getText().toString().trim();

        if ("".equals( name_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入姓名" );
            return false;
        } else {
            if (name_input.length() <= 1) {
                ToastUtils.getInstance( ProveRegisterInfoActivity.this ).showToast( "请输入正确的姓名" );
                return false;
            }
        }
        if ("".equals( cartel_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入随车电话" );
            return false;
        }
        if ("".equals( carnumber_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入车牌号" );
            return false;
        }
        if ("".equals( idnumber_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入身份证号" );
            return false;
        } else {

            if (!VerificationUtil.judgeIDCard( idnumber_input )) {
                ToastUtils.getInstance( ProveRegisterInfoActivity.this ).showToast( "请输入正确的身份证号" );
                return false;
            }
        }
        if ("".equals( address_input )) {
            ToastUtils.getInstance( this ).showToast( "请输入地址" );
            return false;
        } else {
            if (address_input.length() < 5) {
                ToastUtils.getInstance( ProveRegisterInfoActivity.this ).showToast( "详细地址过于简单" );
                return false;
            }
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

    //完善信息接口，完成注册
    private void requestNet_Regist() {
        Map filePath = new HashMap<>();
        filePath.put("A1",mA1);
        filePath.put("B1",mB1);
        filePath.put("C1",mC1);
        filePath.put("C2",mC2);

        Map<String,Object> params = new HashMap<>();
        params.put("id",Hawk.get("userID"));
        params.put("username",name_input);
        params.put( "address", address_input );
        params.put("accessoryphone",cartel_input);
        params.put( "licenseplate", carnumber_input );
        params.put("idcard",idnumber_input);
        params.put("filepath",filePath);
        JSONObject jsonObject = new JSONObject(params);
        
        final ProgressDialog progressDialog = ProgressDialog.show(ProveRegisterInfoActivity.this,"","请稍后······");
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/perfectInformation" )
                .tag( this )
                .upJson( jsonObject )
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response <String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        RegistInfoBean result=gson.fromJson( response.body(), RegistInfoBean.class );
                        if (result.isSuccess()) {// 注册成功
                            AlertDialog.Builder builder = new AlertDialog.Builder(ProveRegisterInfoActivity.this);
                            builder.setTitle("提示")
                                    .setMessage("资料提交成功，请等待审核！")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            finish();
                                        }
                                    }).create().show();
                        } else {
                            ToastUtils.getInstance( ProveRegisterInfoActivity.this ).showToast( result.getMsg() );
                        }

                    }

                    @Override
                    public void onError(Response <String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        ToastUtils.getInstance( ProveRegisterInfoActivity.this ).showToast( response.toString() );
                    }
                } );
    }


}
