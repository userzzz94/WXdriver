package demo.wanxiang.zzz.com.wxdriver.function_activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;

public class routeChangeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgAdd;
    private XRecyclerView xRecyclerView;
    private List<ArrayList> list;
    private RouteChangeAdapter adapter;
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
                EditText etDialogMakeReason = layoutView.findViewById(R.id.etDialogRouteChangeReason);
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
                        alertDialog.cancel();
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

}
