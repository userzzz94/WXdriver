package demo.wanxiang.zzz.com.wxdriver.function_activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.wanxiang.zzz.com.wxdriver.LoginActivity;
import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.bean.ChangeCarInfoBean;
import demo.wanxiang.zzz.com.wxdriver.center_activity.ChangeCarInfoActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;

public class routeChangeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgAdd;
    private XRecyclerView xRecyclerView;
    private List<ArrayList> list;
    private RouteChangeAdapter adapter;

    private String change_reason;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_route_change );
    }

    public void initData() {

    }

    public void initView() {
        imgAdd=findViewById( R.id.ivRouteChangeListAdd );
        imgAdd.setOnClickListener( this );

        xRecyclerView=findViewById( R.id.pager_route_change_xrecyclerview );
        xRecyclerView.setLoadingListener( new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                // 刷新接口获取列表
            }

            @Override
            public void onLoadMore() {

            }
        } );

        list=new ArrayList <>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        xRecyclerView.setLayoutManager(layoutManager);
        adapter=new RouteChangeAdapter( list );
        xRecyclerView.setAdapter( adapter );
        xRecyclerView.setLoadingMoreEnabled( false );
        xRecyclerView.setPullRefreshEnabled( true );

    }

    @Override
    public void onResume() {
        super.onResume();
        xRecyclerView.refresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivRouteChangeListAdd:
                /**点击加号
                 * 弹出dialog弹框
                 */
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View layoutView = LayoutInflater.from(this).inflate(R.layout.dialog_route_change_reason,null);
                TextView tvDialogMakeTitle = layoutView.findViewById(R.id.tvDialogRouteChangeMakeTitle);
                TextView tvDialogMakeSureNO = layoutView.findViewById(R.id.tvDialogRouteChangeMakeSureNO);
                TextView tvDialogMakeSureYes = layoutView.findViewById(R.id.tvDialogRouteChangeMakeSureYes);
                final EditText etDialogMakeReason = layoutView.findViewById(R.id.etDialogRouteChangeReason);

                tvDialogMakeTitle.setText("承运商：");
                tvDialogMakeSureNO.setText("取消");
                tvDialogMakeSureYes.setText("提交");
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
                        //点击提交 申请变更接口
                        change_reason=  etDialogMakeReason.getText().toString().trim();
                        if("".equals( change_reason )){
                           ToastUtils.getInstance( routeChangeActivity.this ).showToast( "请输入变更原因" );
                        }else {
                            requestNet_Change();
                            alertDialog.cancel();
                        }
                    }
                });
                break;

        }
    }

    private class RouteChangeAdapter extends RecyclerView.Adapter <RouteChangeAdapter.RouteChangeAdapterHolder> {
        private List <ArrayList> myOrderList;

        public RouteChangeAdapter(List <ArrayList> list) {
            myOrderList=list;
        }

        @Override
        public RouteChangeAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_my_order_done, parent, false );
            final RouteChangeAdapterHolder holder=new RouteChangeAdapterHolder( view );
            //点击详情
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getAdapterPosition() - 1;
//                    Intent intent = new Intent(routeChangeActivity.this,getOrderDetailActivity.class);
//                    //                    intent.putExtra("orderNumber",myOrderList.get(position).getBh());
//                    startActivity(intent);
//                }
//            });

            return holder;
        }

        @Override
        public void onBindViewHolder(RouteChangeAdapterHolder holder, int position) {
            //MyOrderBean.MyOrderResult myOrderResult = myOrderList.get(position);

            //            holder.tvItemRouteChangeCarNum.setText(myOrderResult.getStateName());
            //            holder.tvItemRouteChangeState.setText(myOrderResult.getStateName());
            //            holder.tvItemRouteChangeManage.setText(myOrderResult.getFhCity());
            //            holder.tvItemRouteChangeReason.setText(myOrderResult.getShCity());
        }

        @Override
        public int getItemCount() {
            return myOrderList.size();
        }

        public class RouteChangeAdapterHolder extends RecyclerView.ViewHolder {
            TextView tvItemRouteChangeCarNum;//车牌号
            TextView tvItemRouteChangeState;//审核状态
            TextView tvItemRouteChangeManage;//原承运商
            TextView tvItemRouteChangeReason; //变更原因

            public RouteChangeAdapterHolder(View itemView) {
                super( itemView );
                tvItemRouteChangeCarNum=(TextView) itemView.findViewById( R.id.item_route_change_list_carnum);
                tvItemRouteChangeState=(TextView) itemView.findViewById( R.id.item_route_change_list_state );
                tvItemRouteChangeManage=(TextView) itemView.findViewById( R.id.item_route_change_list_manage );
                tvItemRouteChangeReason=(TextView) itemView.findViewById( R.id.item_route_change_list_reason );

            }
        }

    }

    //申请变更
    private void requestNet_Change() {
        final ProgressDialog progressDialog = ProgressDialog.show(routeChangeActivity.this,"","请稍后······");
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/carApply" )
                .params("userId", String.valueOf( Hawk.get("userID") ) )
                .params( "licenseplate", String.valueOf( Hawk.get("Licenseplate") ) )
                .params("reason",change_reason)
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response <String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        ChangeCarInfoBean result=gson.fromJson( response.body(), ChangeCarInfoBean.class );
                        if (result.isSuccess()) {// 信息更改成功
                            AlertDialog.Builder builder = new AlertDialog.Builder(routeChangeActivity.this);
                            builder.setTitle("提示")
                                    .setMessage("线控所属承运商更改申请已提交！")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).create().show();
                        } else {
                            ToastUtils.getInstance( routeChangeActivity.this ).showToast( result.getMsg() );
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        ToastUtils.getInstance( routeChangeActivity.this ).showToast( response.toString() );
                    }
                } );
    }

}
