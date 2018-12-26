package demo.wanxiang.zzz.com.wxdriver.center_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;

public class AboutActivity extends BaseActivity {
    private ImageView back;

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
    }
}
