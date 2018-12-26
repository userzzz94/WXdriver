package demo.wanxiang.zzz.com.wxdriver.order_list_fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import demo.wanxiang.zzz.com.wxdriver.bean.DrivergetOrder_Bean;
import demo.wanxiang.zzz.com.wxdriver.bean.ManageList_Bean;
import demo.wanxiang.zzz.com.wxdriver.bean.OrderALLFragment_Bean;
import demo.wanxiang.zzz.com.wxdriver.function_activity.getManageListActivity;
import demo.wanxiang.zzz.com.wxdriver.function_activity.getOrderListActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;

/**
 * Created by leovo on 2018-12-14.
 */

public class MyOrderAllFragment extends Fragment{
    private EditText Searchtxt;
    private String Searchtxt_input;

    private XRecyclerView xRecyclerView;
    private List<OrderALLFragment_Bean.OrderList> mMyOrderList;
    private MyOrderAllAdapter mMyOrderAllAdapter;
    private int mPage;// 当前页数

    private String userName;//接单接口司机姓名
    private int dispatchId;//接单接口派车记录ID

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_my_order_all, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        Searchtxt=view.findViewById(R.id.etMyOrderAllNewSearch );
        xRecyclerView = view.findViewById(R.id.pager_orderAll_xrecyclerview);

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

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        });

        
        mMyOrderList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(layoutManager);
        mMyOrderAllAdapter = new MyOrderAllAdapter(mMyOrderList);
        xRecyclerView.setAdapter(mMyOrderAllAdapter);
        xRecyclerView.setLoadingMoreEnabled(false);
        xRecyclerView.setPullRefreshEnabled(true);
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

    private class MyOrderAllAdapter extends RecyclerView.Adapter<MyOrderAllAdapter.MyOrderAllAdapterHolder>{
        private List<OrderALLFragment_Bean.OrderList> myOrderList;

        public MyOrderAllAdapter(List<OrderALLFragment_Bean.OrderList> list){
            myOrderList = list;
        }
        @Override
        public MyOrderAllAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_order_all,parent,false);
            final MyOrderAllAdapterHolder holder = new MyOrderAllAdapterHolder(view);
            //点击详情
//            holder.btnItemMyOrderAllDetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getAdapterPosition() - 1;
//                    Intent intent = new Intent(getActivity(),Warranty.class);
//                    intent.putExtra("orderNumber",myOrderList.get(position).getBh());
//                    startActivity(intent);
//                }
//            });

            //点击接单
            holder.btnItemMyOrderAllGetorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = holder.getAdapterPosition() - 1;
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    View layoutView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_btn_get_order,null);
                    TextView tvDialogMakeTitle = layoutView.findViewById(R.id.tvDialogMakeTitle);
                    TextView tvDialogMakeSureNO = layoutView.findViewById(R.id.tvDialogMakeSureNO);
                    TextView tvDialogMakeSureYes = layoutView.findViewById(R.id.tvDialogMakeSureYes);
                    tvDialogMakeTitle.setText("预估装载吨数");
                    tvDialogMakeSureNO.setText("取消");
                    tvDialogMakeSureYes.setText("接单");
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
                            getOrderTask();
                            alertDialog.cancel();
                        }
                    });
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(MyOrderAllAdapterHolder holder, int position) {
            OrderALLFragment_Bean.OrderList myOrderResult = myOrderList.get(position);

            dispatchId=myOrderResult.getId();
            userName=myOrderResult.getUser().getUserName();

            holder.tvItemMyOrderAllNumber.setText(myOrderResult.getWaybill().getTransporplan().getCode());
            int stateNum= myOrderResult.getIsaccept();
            if(stateNum==1) {
                holder.tvItemMyOrderAllState.setText( "未接单");
            }else if(stateNum==10) {
                holder.tvItemMyOrderAllState.setText( "已接单");
            }else if(stateNum==20) {
                holder.tvItemMyOrderAllState.setText( "已取消接单");
            }else if(stateNum==30) {
                holder.tvItemMyOrderAllState.setText( "计划停发");
            }
            holder.tvItemMyOrderAllStart.setText(myOrderResult.getWaybill().getTransporplan().getDeliverunit());
            holder.tvItemMyOrderAllEnd.setText(myOrderResult.getWaybill().getTransporplan().getReceiveunit());
            holder.tvItemMyOrderAllTransnum.setText(myOrderResult.getWaybill().getCode());
            holder.tvItemMyOrderAllDate.setText(myOrderResult.getWaybill().getTransporplan().getContractdate());
            holder.tvItemMyOrderAllManagername.setText(myOrderResult.getWaybill().getCarriers().getName());
            int transState=myOrderResult.getState();
            if(transState==1){
                holder.btnItemMyOrderAllTrans.setText("未打卡");
            } else if(transState==5) {
                holder.btnItemMyOrderAllTrans.setText("未审核");
            } else if(transState==10) {
                holder.btnItemMyOrderAllTrans.setText("已审核");
            }else if(transState==20) {
                holder.btnItemMyOrderAllTrans.setText("已装车");
            }else if(transState==30) {
                holder.btnItemMyOrderAllTrans.setText("已铅封");
            }else if(transState==40) {
                holder.btnItemMyOrderAllTrans.setText("已解封");
            }else if(transState==50) {
                holder.btnItemMyOrderAllTrans.setText("已卸货");
            }else if(transState==60) {
                holder.btnItemMyOrderAllTrans.setText("已缴回");
            }
            
        }

        @Override
        public int getItemCount() {
            return myOrderList.size();
        }

        public  class MyOrderAllAdapterHolder extends RecyclerView.ViewHolder{
            TextView tvItemMyOrderAllNumber;//计划单号
            TextView tvItemMyOrderAllState;//接单状态
            TextView tvItemMyOrderAllStart;//发货单位
            TextView tvItemMyOrderAllEnd; //收货单位
            TextView tvItemMyOrderAllTransnum; //运单号
            TextView tvItemMyOrderAllDate; //计划日期
            TextView tvItemMyOrderAllManagername;//承运商名称
            TextView btnItemMyOrderAllTrans; //运输节点
            Button btnItemMyOrderAllDetail;  //详情按钮
            Button btnItemMyOrderAllGetorder; //接单按钮

            public MyOrderAllAdapterHolder(View itemView) {
                super(itemView);
                tvItemMyOrderAllNumber = (TextView)itemView.findViewById(R.id.item_order_all_list_numtxt);
                tvItemMyOrderAllState = (TextView)itemView.findViewById(R.id.item_order_all_list_statetxt);
                tvItemMyOrderAllStart = (TextView)itemView.findViewById(R.id.item_order_all_list_send);
                tvItemMyOrderAllEnd = (TextView)itemView.findViewById(R.id.item_order_all_list_receive);
                tvItemMyOrderAllTransnum = (TextView)itemView.findViewById(R.id.item_order_all_list_order_num);
                tvItemMyOrderAllDate = (TextView)itemView.findViewById(R.id.item_order_all_list_datetxt);
                tvItemMyOrderAllManagername = (TextView)itemView.findViewById(R.id.item_order_all_list_managename);
                btnItemMyOrderAllTrans = (TextView)itemView.findViewById(R.id.item_order_all_list_trans);
                btnItemMyOrderAllDetail = (Button) itemView.findViewById(R.id.item_order_all_list_detail);
                btnItemMyOrderAllGetorder = (Button) itemView.findViewById(R.id.item_order_all_list_getbtn);

            }
        }
    }

    //获取所有任务列表
    private void getOrderList(final int currPage) {
        final ProgressDialog progressDialog=ProgressDialog.show( getActivity(), "", "请稍后······" );
        Searchtxt_input=Searchtxt.getText().toString().trim();
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/getTaskList" )
                .params( "userId", String.valueOf( Hawk.get( "userID" ) ) )
                .params( "state", "1" )
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
                            mMyOrderAllAdapter.notifyDataSetChanged();

                        }else{
                            ToastUtils.getInstance(getActivity()).showToast(bean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response <String> response) {
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

    //司机点击接单
    private void getOrderTask() {
        final ProgressDialog progressDialog=ProgressDialog.show( getActivity(), "", "请稍后······" );
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/confirmOrder" )
                .params( "userId", String.valueOf( Hawk.get( "userID" ) ) )
                .params( "dispatchId", dispatchId )
                .params( "userName",userName )
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        DrivergetOrder_Bean bean=gson.fromJson( response.body(), DrivergetOrder_Bean.class );

                        if(bean.isSuccess()){
                            Intent intent_orderUndo=new Intent( getActivity(), getOrderListActivity.class );
                            intent_orderUndo.putExtra( "order_undo","1" );
                            startActivity( intent_orderUndo );

                        }else{
                            ToastUtils.getInstance(getActivity()).showToast(bean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response <String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        ToastUtils.getInstance( getActivity() ).showToast( response.toString() );
                    }
                } );
    }



}
