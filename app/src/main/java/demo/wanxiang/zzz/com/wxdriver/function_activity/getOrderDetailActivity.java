package demo.wanxiang.zzz.com.wxdriver.function_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;

public class getOrderDetailActivity extends BaseActivity {
    private String orderNumber;// 计划单号
    private String transNumber;//运单号
    private String sendAddress;//发货地点
    private String sendUnit;//发货单位
    private String receiveAddress;//收货单位
    private String receiveUnit;//收货地点
    private String planDate;//计划日期
    private String goodsName;//物资名称
    private String transWeight;//装载吨数
    private String state;//运单状态

    private ImageView back;
    private TextView TVorderNum,TVtransNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_get_order_detail );
    }

    public void initData() {
        Intent intent_orderDetail = getIntent();
        orderNumber = intent_orderDetail.getStringExtra("orderNumber");
    }

    public void initView() {
       back=findViewById( R.id. order_detail_title_back) ;
       back.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       } );
    }
}
