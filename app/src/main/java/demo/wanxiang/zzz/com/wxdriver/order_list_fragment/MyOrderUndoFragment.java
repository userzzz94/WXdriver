package demo.wanxiang.zzz.com.wxdriver.order_list_fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.bean.OrderALLFragment_Bean;
import demo.wanxiang.zzz.com.wxdriver.function_activity.UnloadCarActivity;
import demo.wanxiang.zzz.com.wxdriver.function_activity.getOrderDetailActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;

/**
 * Created by leovo on 2018-12-14.
 */

public class MyOrderUndoFragment extends Fragment {
    private EditText Searchtxt;
    private String Searchtxt_input;

    private XRecyclerView xRecyclerView;
    private List <OrderALLFragment_Bean.OrderList> mMyOrderList;
    private MyOrderUndoAdapter mMyOrderUndoAdapter;
    private int mPage;// 当前页数

    private static final int REQUEST_CODE=7;

    private String userName;//扫码到达接口司机姓名
    private int dispatchId;//扫码到达接口派车记录ID

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_my_order_undo, container, false );
        init( view );
        return view;
    }

    private void init(View view) {
        Searchtxt=view.findViewById(R.id.etMyOrderUndoNewSearch );
        xRecyclerView=view.findViewById( R.id.pager_orderUndo_xrecyclerview );

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
        mMyOrderUndoAdapter=new MyOrderUndoAdapter( mMyOrderList );
        xRecyclerView.setAdapter( mMyOrderUndoAdapter );
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

    private class MyOrderUndoAdapter extends RecyclerView.Adapter <MyOrderUndoAdapter.MyOrderAllAdapterHolder> {
        private List <OrderALLFragment_Bean.OrderList> myOrderList;

        public MyOrderUndoAdapter(List <OrderALLFragment_Bean.OrderList> list) {
            myOrderList=list;
        }

        @Override
        public MyOrderAllAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_my_order_undo, parent, false );
            final MyOrderAllAdapterHolder holder=new MyOrderAllAdapterHolder( view );
            //点击详情
            holder.btnItemMyOrderUndoDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition() - 1;
                    Intent intent = new Intent(getActivity(),getOrderDetailActivity.class);
                    intent.putExtra("orderNumber",myOrderList.get(position).getId());

                    startActivity(intent);
                }
            });

            //卸车确认
            holder.btnItemMyOrderUndoFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition() - 1;
                    Log.e("sweep", myOrderList.get(position).getWaybill().getTransporplan().getCustomer());
                    int state=mMyOrderList.get(position).getState();
                    int sealState=mMyOrderList.get( position ).getWaybill().getTransporplan().getLeadsealing();
                    if( (sealState==1&&state>=30)|| (sealState==0&&state>=20) ) {
                        Intent intent=new Intent( getActivity(), UnloadCarActivity.class );
                        //intent.putExtra("orderNumber",myOrderList.get(position).getBh());
                        startActivity( intent );
                    }else{
                        ToastUtils.getInstance(getActivity()).showToast("订单状态不可进行卸车");
                    }
                }
            });

            //扫码到达
            holder.btnItemMyOrderUndoArrive.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=holder.getAdapterPosition() - 1;
                    //判断state
                    int state=mMyOrderList.get(position).getState();
                    if(state==1){
                        if (ContextCompat.checkSelfPermission( getActivity(), Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions( getActivity(), new String[]{Manifest.permission.CAMERA}, 1 );

                        } else {
                            jumpScanPage();
                        }
                    } else{
                        ToastUtils.getInstance(getActivity()).showToast("订单状态不可进行扫码");
                    }

                }
            } );

            return holder;
        }

        @Override
        public void onBindViewHolder(MyOrderAllAdapterHolder holder, int position) {
            OrderALLFragment_Bean.OrderList myOrderResult = myOrderList.get(position);

            dispatchId=myOrderResult.getId();
            userName=myOrderResult.getUser().getUserName();
            Hawk.put( "dispatchId",dispatchId ) ;
            Hawk.put( "userName",userName );

            holder.tvItemMyOrderUndoNumber.setText(myOrderResult.getWaybill().getTransporplan().getCode());
            int stateNum= myOrderResult.getIsaccept();
            if(stateNum==1) {
                holder.tvItemMyOrderUndoState.setText( "未接单");
            }else if(stateNum==10) {
                holder.tvItemMyOrderUndoState.setText( "已接单");
            }else if(stateNum==20) {
                holder.tvItemMyOrderUndoState.setText( "已取消接单");
            }else if(stateNum==30) {
                holder.tvItemMyOrderUndoState.setText( "计划停发");
            }
            holder.tvItemMyOrderUndoStart.setText(myOrderResult.getWaybill().getTransporplan().getDeliverunit());
            holder.tvItemMyOrderUndoEnd.setText(myOrderResult.getWaybill().getTransporplan().getReceiveunit());
            holder.tvItemMyOrderUndoTransnum.setText(myOrderResult.getWaybill().getCode());
            holder.tvItemMyOrderUndoDate.setText(myOrderResult.getWaybill().getTransporplan().getContractdate());
            holder.tvItemMyOrderUndoManagername.setText(myOrderResult.getWaybill().getCarriers().getName());
            int transState=myOrderResult.getState();
            if(transState==1){
                holder.btnItemMyOrderUndoTrans.setText("未打卡");
            } else if(transState==5) {
                holder.btnItemMyOrderUndoTrans.setText("未审核");
            } else if(transState==10) {
                holder.btnItemMyOrderUndoTrans.setText("已审核");
            }else if(transState==20) {
                holder.btnItemMyOrderUndoTrans.setText("已装车");
            }else if(transState==30) {
                holder.btnItemMyOrderUndoTrans.setText("已铅封");
            }else if(transState==40) {
                holder.btnItemMyOrderUndoTrans.setText("已解封");
            }else if(transState==50) {
                holder.btnItemMyOrderUndoTrans.setText("已卸货");
            }else if(transState==60) {
                holder.btnItemMyOrderUndoTrans.setText("已缴回");
            }
        }

        @Override
        public int getItemCount() {
            return myOrderList.size();
        }

        public class MyOrderAllAdapterHolder extends RecyclerView.ViewHolder {
            TextView tvItemMyOrderUndoNumber;//计划单号
            TextView tvItemMyOrderUndoState;//接单状态
            TextView tvItemMyOrderUndoStart;//发货单位
            TextView tvItemMyOrderUndoEnd; //收货单位
            TextView tvItemMyOrderUndoTransnum; //运单号
            TextView tvItemMyOrderUndoDate; //计划日期
            TextView tvItemMyOrderUndoManagername;//承运商名称
            TextView btnItemMyOrderUndoTrans; //运输节点
            Button btnItemMyOrderUndoDetail;  //详情按钮
            Button btnItemMyOrderUndoArrive; //报到按钮
            Button btnItemMyOrderUndoCancel; //取消按钮
            Button btnItemMyOrderUndoFinish; //卸车按钮

            public MyOrderAllAdapterHolder(View itemView) {
                super( itemView );
                tvItemMyOrderUndoNumber=(TextView) itemView.findViewById( R.id.item_order_undo_list_numtxt );
                tvItemMyOrderUndoState=(TextView) itemView.findViewById( R.id.item_order_undo_list_statetxt );
                tvItemMyOrderUndoStart=(TextView) itemView.findViewById( R.id.item_order_undo_list_send );
                tvItemMyOrderUndoEnd=(TextView) itemView.findViewById( R.id.item_order_undo_list_receive );
                tvItemMyOrderUndoTransnum=(TextView) itemView.findViewById( R.id.item_order_undo_list_order_num );
                tvItemMyOrderUndoDate=(TextView) itemView.findViewById( R.id.item_order_undo_list_datetxt );
                tvItemMyOrderUndoManagername=(TextView) itemView.findViewById( R.id.item_order_undo_list_managename );
                btnItemMyOrderUndoTrans=(TextView) itemView.findViewById( R.id.item_order_undo_list_trans );
                btnItemMyOrderUndoDetail=(Button) itemView.findViewById( R.id.item_order_undo_list_detailbtn );
                btnItemMyOrderUndoArrive=(Button) itemView.findViewById( R.id.item_order_undo_list_arrivebtn );
                btnItemMyOrderUndoCancel=(Button) itemView.findViewById( R.id.item_order_undo_list_cancelbtn );
                btnItemMyOrderUndoFinish=(Button) itemView.findViewById( R.id.item_order_undo_list_finishbtn );

            }
        }

    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    jumpScanPage();
                } else {
                    Toast.makeText( getActivity(), "拒绝", Toast.LENGTH_LONG ).show();
                }
            default:
                break;
        }

    }

    private void jumpScanPage() {
        Log.e( "scan", "jumpScanPage" );
        Intent intent_scan=new Intent( getActivity(), CaptureActivity.class );
        getActivity().startActivityForResult( intent_scan, REQUEST_CODE );

        Log.e( "scan", "startActivityForResult" );
    }

    //获取任务列表
    private void getOrderList(final int currPage) {
        final ProgressDialog progressDialog=ProgressDialog.show( getActivity(), "", "请稍后······" );
        Searchtxt_input=Searchtxt.getText().toString().trim();
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/getTaskList" )
                .params( "userId", String.valueOf( Hawk.get( "userID" ) ) )
                .params( "state", "10" )
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
                            mMyOrderUndoAdapter.notifyDataSetChanged();

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
