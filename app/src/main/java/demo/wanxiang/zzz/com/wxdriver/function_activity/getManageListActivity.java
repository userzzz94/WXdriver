package demo.wanxiang.zzz.com.wxdriver.function_activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.bean.FocusCarriers_Bean;
import demo.wanxiang.zzz.com.wxdriver.bean.ManageList_Bean;
import demo.wanxiang.zzz.com.wxdriver.utils.BaseActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;

public class getManageListActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgAdd;
    private EditText Searchtxt;
    private XRecyclerView xRecyclerView;
    private List <ManageList_Bean.ManageList> list;
    private ManageListAdapter adapter;

    private String Searchtxt_input;
    private String carrierId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_get_manage_list );
    }

    public void initData() {

    }

    public void initView() {
        imgAdd=findViewById( R.id.ivMyManagerListAdd );
        imgAdd.setOnClickListener( this );

        Searchtxt=findViewById( R.id.etManageListNewSearch );
        xRecyclerView=findViewById( R.id.xrvManageListSearch );

        Searchtxt.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mHandler.hasMessages( 1 )) {
                    mHandler.removeMessages( 1 );
                }
                mHandler.sendEmptyMessageDelayed( 1, 1000 );
            }
        } );

        xRecyclerView.setLoadingListener( new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //获取承运商列表
                getCarrierList();
                //String content = Searchtxt.getText().toString().trim();
                //                if(content.equals("")){
                //                    //获取承运商列表
                //                    getCarrierList();
                //                }else{
                //                    //搜索承运商
                //                    //lookupContacts(Searchtxt.getText().toString().trim());
                //                }
            }

            @Override
            public void onLoadMore() {

            }
        } );
        list=new ArrayList <>();
        LinearLayoutManager layoutManager=new LinearLayoutManager( this );
        xRecyclerView.setLayoutManager( layoutManager );
        adapter=new ManageListAdapter( list );
        xRecyclerView.setAdapter( adapter );
        xRecyclerView.setLoadingMoreEnabled( false );
        xRecyclerView.setPullRefreshEnabled( true );

    }

    private Handler mHandler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            xRecyclerView.refresh();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        xRecyclerView.refresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivMyManagerListAdd:
                /**点击加号
                 * 1.ImageView变为不可见
                 * 2.获取未关注承运商列表
                 * 3.item中的按钮txt变为关注
                 */
//                imgAdd.setVisibility(View.INVISIBLE);
//                unFocusCarrierList();
                Intent intent=new Intent(getManageListActivity.this,FocusCarriersActivity.class);
                startActivity( intent );
                break;

        }
    }


    private class ManageListAdapter extends RecyclerView.Adapter <ManageListAdapter.ContactsViewHolder> {
        private List <ManageList_Bean.ManageList> manageList;

        public ManageListAdapter(List <ManageList_Bean.ManageList> list) {
            manageList=list;
        }

        @Override
        public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_get_manage_list, parent, false );
            final ContactsViewHolder holder=new ContactsViewHolder( view );

            //点击关注或取消关注
            holder.tvItemManageConcerned.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=holder.getAdapterPosition() - 1;
                    carrierId=String.valueOf( list.get( position ).getCarrierid() );
                    /**
                     * 点击关注 接口 更新已关注列表  focusCarriers();
                     * 点击取消关注 接口 更新未关注列表
                     */
                    if(holder.tvItemManageConcerned.getText().toString().trim()=="取消关注") {
                        cancelCarriers();
                    }else{
                        focusCarriers();
                    }

                }
            } );

            return holder;
        }

        @Override
        public void onBindViewHolder(ContactsViewHolder holder, int position) {
            ManageList_Bean.ManageList listItem = manageList.get(position);
            holder.tvItemManageName.setText(listItem.getCarriers().getName());
            holder.tvItemManageTel.setText(listItem.getCarriers().getTel());
            holder.tvItemManageAddress.setText( listItem.getCarriers().getAddress() );
            holder.tvItemManageContact .setText( listItem.getCarriers().getContacts() );
            //设置是否关注文字
            if(listItem.getId() == 0){
                holder.tvItemManageConcerned.setText("关注");
            }else{
                holder.tvItemManageConcerned.setText("取消关注");
            }
        }

        @Override
        public int getItemCount() {
            return manageList.size();
        }

        public class ContactsViewHolder extends RecyclerView.ViewHolder {
            TextView tvItemManageName;
            TextView tvItemManageTel;
            TextView tvItemManageContact;
            TextView tvItemManageAddress;
            TextView tvItemManageConcerned;

            public ContactsViewHolder(View itemView) {
                super( itemView );
                tvItemManageName=(TextView) itemView.findViewById( R.id.item_get_manage_list_nametxt );
                tvItemManageConcerned=(TextView) itemView.findViewById( R.id.item_get_manage__list_concerned );
                tvItemManageTel=(TextView) itemView.findViewById( R.id.item_get_manage_list_tel );
                tvItemManageContact=(TextView) itemView.findViewById( R.id.item_get_manage_list_contact );
                tvItemManageAddress=(TextView) itemView.findViewById( R.id.item_get_manage_list_address );
            }
        }
    }

    //获取所有承运商列表接口
    private void getCarrierList() {
        final ProgressDialog progressDialog=ProgressDialog.show( getManageListActivity.this, "", "请稍后······" );
        Searchtxt_input=Searchtxt.getText().toString().trim();
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/getCarrierDriver" )
                .params( "userId", String.valueOf( Hawk.get( "userID" ) ) )
                .params( "searchValue", Searchtxt_input )
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response <String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        ManageList_Bean bean=gson.fromJson( response.body(), ManageList_Bean.class );

                        if(bean.isSuccess()){
                            list.clear();
                            for(ManageList_Bean.ManageList ManageList:bean.getData())

                            list.add( ManageList );

                            xRecyclerView.refreshComplete();
                            adapter.notifyDataSetChanged();

                        }else{
                            ToastUtils.getInstance(getManageListActivity.this).showToast( bean.getMsg( ));
                        }
                    }

                    @Override
                    public void onError(Response <String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        ToastUtils.getInstance( getManageListActivity.this ).showToast( response.toString() );
                    }
                } );
    }

    //获取已关注承运商列表接口
    private void getFocusCarrierList() {
        final ProgressDialog progressDialog=ProgressDialog.show( getManageListActivity.this, "", "请稍后······" );
        Searchtxt_input=Searchtxt.getText().toString().trim();
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/getCarrierDriver" )
                .params( "userId", String.valueOf( Hawk.get( "userID" ) ) )
                .params( "searchValue", Searchtxt_input )
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response <String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        ManageList_Bean bean=gson.fromJson( response.body(), ManageList_Bean.class );

                        if(bean.isSuccess()){
                            list.clear();
                            for(ManageList_Bean.ManageList ManageList:bean.getData())
                                if(ManageList.getId()!=0) {
                                    list.add( ManageList );
                                }
                            xRecyclerView.refreshComplete();
                            adapter.notifyDataSetChanged();

                        }else{
                            ToastUtils.getInstance(getManageListActivity.this).showToast( bean.getMsg( ));
                        }
                    }

                    @Override
                    public void onError(Response <String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        ToastUtils.getInstance( getManageListActivity.this ).showToast( response.toString() );
                    }
                } );
    }

    //获取未关注承运商列表接口
    private void unFocusCarrierList() {
        final ProgressDialog progressDialog=ProgressDialog.show( getManageListActivity.this, "", "请稍后······" );
        Searchtxt_input=Searchtxt.getText().toString().trim();
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/getCarrierDriver" )
                .params( "userId", String.valueOf( Hawk.get( "userID" ) ) )
                .params( "searchValue", Searchtxt_input )
                .execute( new StringCallback() {
            @Override
            public void onSuccess(Response <String> response) {
                progressDialog.cancel();
                Gson gson=new Gson();
                ManageList_Bean bean=gson.fromJson( response.body(), ManageList_Bean.class );

                if(bean.isSuccess()){
                    list.clear();
                    for(ManageList_Bean.ManageList ManageList:bean.getData())
                    if(ManageList.getId()==0) {
                        list.add( ManageList );
                        xRecyclerView.refreshComplete();
                        adapter.notifyDataSetChanged();
                    }


                }else{
                    ToastUtils.getInstance(getManageListActivity.this).showToast( bean.getMsg( ));
                }
            }

            @Override
            public void onError(Response <String> response) {
                super.onError( response );
                progressDialog.cancel();
                ToastUtils.getInstance( getManageListActivity.this ).showToast( response.toString() );
            }
        } );
    }

    //点击取消关注承运商
    private void cancelCarriers() {
        final ProgressDialog progressDialog=ProgressDialog.show( getManageListActivity.this, "", "请稍后······" );
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/cancelFocusCarriers" )
                .params( "userId", String.valueOf( Hawk.get( "userID" ) ) )
                .params( "carrierId", carrierId )
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response <String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        FocusCarriers_Bean bean=gson.fromJson( response.body(), FocusCarriers_Bean.class );

                        if(bean.isSuccess()){
                            ToastUtils.getInstance(getManageListActivity.this).showToast( "取消关注成功");
                            xRecyclerView.refresh();
                            //getFocusCarrierList();

                        }else{
                            ToastUtils.getInstance(getManageListActivity.this).showToast( bean.getMsg( ));
                        }
                    }

                    @Override
                    public void onError(Response <String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        ToastUtils.getInstance( getManageListActivity.this ).showToast( response.toString() );
                    }
                } );
    }

    //点击关注承运商
    private void focusCarriers() {
        final ProgressDialog progressDialog=ProgressDialog.show( getManageListActivity.this, "", "请稍后······" );
        OkGo. <String>post( UrlUtil.URL_Prefix + "driverapi/focusCarriers" )
                .params( "userId", String.valueOf( Hawk.get( "userID" ) ) )
                .params( "carrierId", carrierId )
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response <String> response) {
                        progressDialog.cancel();
                        Gson gson=new Gson();
                        FocusCarriers_Bean bean=gson.fromJson( response.body(), FocusCarriers_Bean.class );

                        if(bean.isSuccess()){
                            ToastUtils.getInstance(getManageListActivity.this).showToast( "关注成功");
                            xRecyclerView.refresh();
                            //getCarrierList();

                        }else{
                            ToastUtils.getInstance(getManageListActivity.this).showToast( bean.getMsg( ));
                        }
                    }

                    @Override
                    public void onError(Response <String> response) {
                        super.onError( response );
                        progressDialog.cancel();
                        ToastUtils.getInstance( getManageListActivity.this ).showToast( response.toString() );
                    }
                } );
    }

}
