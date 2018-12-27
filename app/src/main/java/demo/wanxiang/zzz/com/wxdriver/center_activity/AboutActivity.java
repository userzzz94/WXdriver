package demo.wanxiang.zzz.com.wxdriver.center_activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;

import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.bean.VersionBean;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;

public class AboutActivity extends BaseActivity {
    private ImageView back;
    private TextView mTvAboutVersion;
    private Button mBtnAboutUpdate;

    private static final int REQUEST_PERMISSION_STORAGE = 0x01;
    private String mUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about );
    }

    public void initData() {

    }

    public void initView() {
       back =findViewById( R.id.activity_about_title_back );
       back.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       } );

       mTvAboutVersion=findViewById( R.id.activity_about_tv_version_name );
       mTvAboutVersion.setText( "版本号：" + getCurrentVersionName() );

       mBtnAboutUpdate=findViewById( R.id.activity_about_btn_update );
       mBtnAboutUpdate.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               requestNet_Version();
           }
       } );
    }

    /**
     * 获取当前app的版本名
     * @return
     */
    private String getCurrentVersionName(){
        String versionName = null;
        try {
            PackageManager mPackageManager = this.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(this.getPackageName(),0);
            versionName = mPackageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
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
        final ProgressDialog progressDialog = ProgressDialog.show(AboutActivity.this,"","请稍后······");
        OkGo.<String>post( UrlUtil.URL_Prefix + "api/updatedVersion?type=S&oldVersion=" + getCurrentVersion())
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.dismiss();
                        Gson gson = new Gson();
                        final VersionBean versionBean = gson.fromJson(response.body(),VersionBean.class);
                        String message = versionBean.getMsg();
                        if(versionBean.isSuccess() && versionBean.getData() != null){
                            mUrl = versionBean.getData().getUrl();
                            AlertDialog.Builder builder = new AlertDialog.Builder(AboutActivity.this);
                            TextView tv = new TextView(AboutActivity.this);
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
                                            Uri uri = Uri.parse(UrlUtil.URL_Prefix_UPDATE_URL + mUrl);
                                            Intent it = new Intent(Intent.ACTION_VIEW,uri);
                                            startActivity(it);
                                        }
                                    })
                                    .setPositiveButton("应用内更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if(ContextCompat.checkSelfPermission(AboutActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                                                ActivityCompat.requestPermissions(AboutActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION_STORAGE);
                                            }else{
                                                downloadAPK(mUrl);
                                            }
                                        }
                                    }).create().show();
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(AboutActivity.this);
                            builder.setTitle("提示")
                                    .setMessage(message)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).create().show();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                        Toast.makeText(AboutActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(AboutActivity.this,"权限被禁止，无法下载文件！",Toast.LENGTH_SHORT).show();
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
        final ProgressDialog progressDialog = new ProgressDialog(AboutActivity.this);
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
                        Toast.makeText(AboutActivity.this,"下载成功",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        if(Build.VERSION.SDK_INT >= 24) {//判读版本是否在7.0以上
                            File filePath = new File( Environment.getExternalStorageDirectory(), "download");
                            File newFile = new File(filePath, "WanXiangDriver.apk");
                            Uri contentUri = FileProvider.getUriForFile(AboutActivity.this, "demo.wanxiang.zzz.com.wxdriver.fileprovider", newFile);
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
                        Toast.makeText(AboutActivity.this,"下载失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
