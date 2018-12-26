package demo.wanxiang.zzz.com.wxdriver.utils;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.orhanobut.hawk.Hawk;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by leovo on 2018-12-12.
 */

public class MyApplication extends Application {
    private static final String TAG = "Init";

    public static boolean isDebug = true;

    @Override
    public void onCreate() {


        super.onCreate();

        //以下可以写 各种第三方空间的初始化.

        //OKGO
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.readTimeout( OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS );
        builder.writeTimeout( OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS );
        builder.connectTimeout( OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS );
        HttpsUtils.SSLParams sslParams=HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory( sslParams.sSLSocketFactory, sslParams.trustManager );
        //设置cookie
        builder.cookieJar( new CookieJarImpl( new SPCookieStore( this ) ) );

        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor( "OkGo" );
        loggingInterceptor.setPrintLevel( HttpLoggingInterceptor.Level.BODY );
        loggingInterceptor.setColorLevel( Level.INFO );
        builder.addInterceptor( loggingInterceptor );
        OkGo.getInstance().init( this ).setOkHttpClient( builder.build() );

        Hawk.init( this ).build();

        //初始化ZXING包
        ZXingLibrary.initDisplayOpinion(this);

    }

}
