package demo.wanxiang.zzz.com.wxdriver.order_list_fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.bean.OrderALLFragment_Bean;
import demo.wanxiang.zzz.com.wxdriver.function_activity.getOrderDetailActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;

/**
 * Created by leovo on 2018-12-14.
 */

public class MyOrderDoneFragment extends Fragment{
    private EditText Searchtxt;
    private String Searchtxt_input;

    private XRecyclerView xRecyclerView;
    private List<OrderALLFragment_Bean.OrderList> mMyOrderList;
    private MyOrderDoneAdapter mMyOrderDoneAdapter;
    private int mPage;// 当前页数

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_my_order_done, container, false );
        init( view );
        return view;
    }

    private void init(View view) {
        Searchtxt=view.findViewById(R.id.etMyOrderDoneNewSearch );
        xRecyclerView=view.findViewById( R.id.pager_orderDone_xrecyclerview );

        Searchtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mHandler.hasMessages(1)){
                    mHandler.removeMessages(1);
                }
                mHandler.sendEmptyMessageDelayed(1,1000);
            }
        });

        xRecyclerView.setLoadingListener( new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                getOrderList(mPage);
                xRecyclerView.setLoadingMoreEnabled(true);
            }

            @Override
            public void onLoadMore() {
                mPage += 1;
                getOrderList(mPage);
            }
        } );

        mMyOrderList=new ArrayList <>();
        LinearLayoutManager layoutManager=new LinearLayoutManager( getActivity() );
        xRecyclerView.setLayoutManager( layoutManager );
        mMyOrderDoneAdapter=new MyOrderDoneAdapter( mMyOrderList );
        xRecyclerView.setAdapter( mMyOrderDoneAdapter );
        xRecyclerView.setLoadingMoreEnabled( false );
        xRecyclerView.setPullRefreshEnabled( true );
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            xRecyclerView.refresh();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        xRecyclerView.refresh();
    }

    private class MyOrderDoneAdapter extends RecyclerView.Adapter <MyOrderDoneAdapter.MyOrderDoneAdapterHolder> {
        private List <OrderALLFragment_Bean.OrderList> myOrderList;

        public MyOrderDoneAdapter(List <OrderALLFragment_Bean.OrderList> list) {
            myOrderList=list;
        }

        @Override
        public MyOrderDoneAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_my_order_done, parent, false );
            final MyOrderDoneAdapterHolder holder=new MyOrderDoneAdapterHolder( view );
            //点击详情
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition() - 1;
                    Intent intent = new Intent(getActivity(),getOrderDetailActivity.class);
//                    intent.putExtra("orderNumber",myOrderList.get(position).getBh());
                    startActivity(intent);
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(MyOrderDoneAdapterHolder holder, int position) {
            OrderALLFragment_Bean.OrderList myOrderResult = myOrderList.get(position);

            holder.tvItemMyOrderDoneNumber.setText(myOrderResult.getWaybill().getTransporplan().getCode());
            int stateNum= myOrderResult.getIsaccept();
            if(stateNum==1) {
                holder.tvItemMyOrderDoneState.setText( "未接单");
            }else if(stateNum==10) {
                holder.tvItemMyOrderDoneState.setText( "已接单");
            }else if(stateNum==20) {
                holder.tvItemMyOrderDoneState.setText( "已取消接单");
            }else if(stateNum==30) {
                holder.tvItemMyOrderDoneState.setText( "计划停发");
            }
            holder.tvItemMyOrderDoneStart.setText(myOrderResult.getWaybill().getTransporplan().getDeliverunit());
            holder.tvItemMyOrderDoneEnd.setText(myOrderResult.getWaybill().getTransporplan().getReceiveunit());
            holder.tvItemMyOrderDoneTransnum.setText(myOrderResult.getWaybill().getCode());
            holder.tvItemMyOrderDoneDate.setText(myOrderResult.getWaybill().getTransporplan().getContractdate());
            holder.tvItemMyOrderDoneManagername.setText(myOrderResult.getWaybill().getCarriers().getName());
            int transState=myOrderResult.getState();
            if(transState==1){
                holder.btnItemMyOrderDoneTrans.setText("未打卡");
            } else if(transState==5) {
                holder.btnItemMyOrderDoneTrans.setText("未审核");
            } else if(transState==10) {
                holder.btnItemMyOrderDoneTrans.setText("已审核");
            }else if(transState==20) {
                holder.btnItemMyOrderDoneTrans.setText("已装车");
            }else if(transState==30) {
                holder.btnItemMyOrderDoneTrans.setText("已铅封");
            }else if(transState==40) {
                holder.btnItemMyOrderDoneTrans.setText("已解封");
            }else if(transState==50) {
                holder.btnItemMyOrderDoneTrans.setText("已卸货");
            }else if(transState==60) {
                holder.btnItemMyOrderDoneTrans.setText("已缴回");
            }
        }

        @Override
        public int getItemCount() {
            return myOrderList.size();
        }

        public class MyOrderDoneAdapterHolder extends RecyclerView.ViewHolder {
            TextView tvItemMyOrderDoneNumber;//计划单号
            TextView tvItemMyOrderDoneState;//接单状态
            TextView tvItemMyOrderDoneStart;//发货单位
            TextView tvItemMyOrderDoneEnd; //收货单位
            TextView tvItemMyOrderDoneTransnum; //运单号
            TextView tvItemMyOrderDoneDate; //计划日期
            TextView tvItemMyOrderDoneManagername;//承运商名称
            TextView btnItemMyOrderDoneTrans; //运输节点

            public MyOrderDoneAdapterHolder(View itemView) {
                super( itemView );
                tvItemMyOrderDoneNumber=(TextView) itemView.findViewById( R.id.item_order_done_list_numtxt );
                tvItemMyOrderDoneState=(TextView) itemView.findViewById( R.id.item_order_done_list_statetxt );
                tvItemMyOrderDoneStart=(TextView) itemView.findViewById( R.id.item_order_done_list_send );
                tvItemMyOrderDoneEnd=(TextView) itemView.findViewById( R.id.item_order_done_list_receive );
                tvItemMyOrderDoneTransnum=(TextView) itemView.findViewById( R.id.item_order_done_list_order_num );
                tvItemMyOrderDoneDate=(TextView) itemView.findViewById( R.id.item_order_done_list_datetxt );
                tvItemMyOrderDoneManagername=(TextView) itemView.findViewById( R.id.item_order_done_list_managename );
                btnItemMyOrderDoneTrans=(TextView) itemView.findViewById( R.id.item_order_done_list_trans );

            }
        }

    }

    //获取任务列表
    private void getOrderList(final int currPage) {
        final ProgressDialog progressDialog=ProgressDialog.show( getActivity(), "", "请稍后······" );
        Searchtxt_input=Searchtxt.getText().toString().trim();
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/getTaskList" )
                .params( "userId", String.valueOf( Hawk.get( "userID" ) ) )
                .params( "state", "40" )
                .params( "searchValue",Searchtxt_input )
                .params( "pageNum",currPage )
                .params( "pageSize", 10)
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.cancel();
                        if(currPage == 1){
                            xRecyclerView.refreshComplete();// 告诉适配器, 刷新完成
                        }else{
                            xRecyclerView.loadMoreComplete();// 告诉适配器, 加载更多完成
                        }
                        Gson gson=new Gson();
                        OrderALLFragment_Bean bean=gson.fromJson( response.body(), OrderALLFragment_Bean.class );

                        if(bean.isSuccess()){
                            if(currPage == 1){// 当是下拉刷新的时候,需要将list中的数据清空.
                                mMyOrderList.clear();
                            }
                            for(OrderALLFragment_Bean.OrderList OrderList:bean.getData())

                            mMyOrderList.add( OrderList );
                            mMyOrderDoneAdapter.notifyDataSetChanged();

                        }else{
                            ToastUtils.getInstance(getActivity()).showToast(bean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        if (mPage ==1){
                            xRecyclerView .refreshComplete() ;
                        } else {
                            xRecyclerView .loadMoreComplete() ;
                        }
                        ToastUtils.getInstance( getActivity() ).showToast( response.toString() );
                    }
                } );
    }


}
