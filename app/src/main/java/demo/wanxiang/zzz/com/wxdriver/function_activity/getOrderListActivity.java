package demo.wanxiang.zzz.com.wxdriver.function_activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.bean.DrivergetOrder_Bean;
import demo.wanxiang.zzz.com.wxdriver.bean.driverArriveSure_Bean;
import demo.wanxiang.zzz.com.wxdriver.order_list_fragment.MyOrderAllFragment;
import demo.wanxiang.zzz.com.wxdriver.order_list_fragment.MyOrderCancelFragment;
import demo.wanxiang.zzz.com.wxdriver.order_list_fragment.MyOrderDoneFragment;
import demo.wanxiang.zzz.com.wxdriver.order_list_fragment.MyOrderUndoFragment;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;

public class getOrderListActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mIvMyOrderListBack;
    private TextView mTvMyOrderListAll;
    private TextView mTvMyOrderListUndo;
    private TextView mTvMyOrderListDone;
    private TextView mTvMyOrderListCancel;
    private ViewPager mVgMyOrderList;

    private List<Fragment> mList;
    private TabFragmentPagerAdapter mAdapter;

    private static final int REQUEST_CODE=7;

    private String result;//解析扫码信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_get_order_list );

    }

    public void initData() {
        
        Intent intent_orderUndo = getIntent();
        String orderUndo = intent_orderUndo.getStringExtra("order_undo");
        if(orderUndo != null) {
            mVgMyOrderList.setCurrentItem( Integer.parseInt( orderUndo ) );
        }
        Intent intent_orderDone = getIntent();
        String orderDone = intent_orderDone.getStringExtra("order_done");
        if(orderDone != null) {
            mVgMyOrderList.setCurrentItem( Integer.parseInt( orderDone ) );
        }
    }

    public void initView() {
        mIvMyOrderListBack = findViewById(R.id.ivMyOrderListBack);
        mTvMyOrderListAll = findViewById(R.id.tvMyOrderListAll);
        mTvMyOrderListUndo = findViewById(R.id.tvMyOrderListUndo);
        mTvMyOrderListDone = findViewById(R.id.tvMyOrderListDone);
        mTvMyOrderListCancel = findViewById(R.id.tvMyOrderListCancel);
        mVgMyOrderList = findViewById(R.id.vgMyOrderList);

        mIvMyOrderListBack.setOnClickListener(this);
        mTvMyOrderListAll.setOnClickListener(this);
        mTvMyOrderListUndo.setOnClickListener(this);
        mTvMyOrderListDone.setOnClickListener(this);
        mTvMyOrderListCancel.setOnClickListener( this );
        mVgMyOrderList.setOnPageChangeListener(new MyPagerChangeListener());

        mList = new ArrayList<>();
        mList.add(new MyOrderAllFragment());
        mList.add(new MyOrderUndoFragment());
        mList.add(new MyOrderDoneFragment());
        mList.add(new MyOrderCancelFragment());
        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), mList);
        mVgMyOrderList.setAdapter(mAdapter);

        mVgMyOrderList.setCurrentItem(0);  //初始化显示第一个页面
        mTvMyOrderListAll.setTextAppearance(this,R.style.order_list_tab_txt_selected);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivMyOrderListBack:
                finish();
                break;
            case R.id.tvMyOrderListAll:
                mVgMyOrderList.setCurrentItem(0);
                break;
            case R.id.tvMyOrderListUndo:
                mVgMyOrderList.setCurrentItem(1);
                break;
            case R.id.tvMyOrderListDone:
                mVgMyOrderList.setCurrentItem(2);
                break;
            case R.id.tvMyOrderListCancel:
                mVgMyOrderList.setCurrentItem(3);
                break;
        }
    }

    private class TabFragmentPagerAdapter extends FragmentPagerAdapter {
        private FragmentManager mfragmentManager;
        private List<Fragment> mlist;

        public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.mlist = list;
        }

        @Override
        public Fragment getItem(int arg0) {
            return mlist.get(arg0);//显示第几个页面
        }

        @Override
        public int getCount() {
            return mlist.size();//有几个页面
        }
    }

    private class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    mTvMyOrderListAll.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt_selected);
                    mTvMyOrderListUndo.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    mTvMyOrderListDone.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    mTvMyOrderListCancel.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    break;
                case 1:
                    mTvMyOrderListAll.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    mTvMyOrderListUndo.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt_selected);
                    mTvMyOrderListDone.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    mTvMyOrderListCancel.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    break;
                case 2:
                    mTvMyOrderListAll.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    mTvMyOrderListUndo.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    mTvMyOrderListDone.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt_selected);
                    mTvMyOrderListCancel.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    break;
                case 3:
                    mTvMyOrderListAll.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    mTvMyOrderListUndo.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    mTvMyOrderListDone.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt);
                    mTvMyOrderListCancel.setTextAppearance(getOrderListActivity.this,R.style.order_list_tab_txt_selected);
                    break;
            }
        }
    }


    // 扫码onActivityResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log .e("scan","onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==REQUEST_CODE) {

            if (data == null) {

                Toast.makeText( this, "扫描二维码失败:", Toast.LENGTH_LONG ).show();

            }else {

                Bundle bundle = data.getExtras();
                Log.e( "scan", "requestCode" );
                if (bundle == null) {
                    Toast.makeText( this, "未查询到在该厂区的订单信息", Toast.LENGTH_LONG ).show();
                    return;
                }
                if (bundle.getInt( CodeUtils.RESULT_TYPE ) == CodeUtils.RESULT_SUCCESS) {
                    Log.e( "scan", "RESULT_OK" );

                    //这是拿到解析扫描到的信息，并转成字符串
                    result = bundle.getString( CodeUtils.RESULT_STRING );

                    //解析扫到的二维码后就弹出确认到达弹框;
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    View layoutView = LayoutInflater.from(this).inflate(R.layout.dialog_get_order_list_arrive,null);
                    TextView tvDialogMakeTitle = layoutView.findViewById(R.id.tvDialogArriveMakeTitle);
                    TextView tvDialogMakeSureNO = layoutView.findViewById(R.id.tvDialogArriveMakeSureNO);
                    TextView tvDialogMakeSureYes = layoutView.findViewById(R.id.tvDialogArriveMakeSureYes);
                    tvDialogMakeTitle.setText("确认到达？");
                    tvDialogMakeSureNO.setText("取消");
                    tvDialogMakeSureYes.setText("确认");
                    builder.setView(layoutView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    tvDialogMakeSureNO.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });
                    tvDialogMakeSureYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //司机确认到达
                            driverArriveSure();
                            alertDialog.cancel();
                        }
                    });


                } else if (bundle.getInt( CodeUtils.RESULT_TYPE ) == CodeUtils.RESULT_FAILED) {
                    Log.e( "scan", "RESULT_CANCELED" );
                    //否则提示解析二维码失败
                    Toast.makeText( this, "解析二维码失败:", Toast.LENGTH_LONG ).show();

                }
            }
        }

    }

    //司机扫码到达 确认
    private void driverArriveSure() {
        final ProgressDialog progressDialog=ProgressDialog.show( getOrderListActivity.this, "", "请稍后······" );
        int dispatchId=Hawk.get( "dispatchId" ) ;
        String userName=Hawk.get( "userName" );

        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/sweepCode" )
                .params( "userId", String.valueOf( Hawk.get( "userID" ) ) )
                .params( "dispatchId", dispatchId )
                .params( "Value", result)
                .params( "userName",userName )
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        driverArriveSure_Bean bean=gson.fromJson( response.body(), driverArriveSure_Bean.class );

                        if(bean.isSuccess()){
                           finish();

                        }else{
                            ToastUtils.getInstance(getOrderListActivity.this).showToast(bean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        ToastUtils.getInstance( getOrderListActivity.this ).showToast( response.toString() );
                    }
                } );
    }



}
