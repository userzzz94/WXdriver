package demo.wanxiang.zzz.com.wxdriver.main_driver_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.function_activity.getManageListActivity;
import demo.wanxiang.zzz.com.wxdriver.function_activity.getOrderListActivity;
import demo.wanxiang.zzz.com.wxdriver.function_activity.routeChangeActivity;

/**
 * Created by leovo on 2018-12-14.
 */

public class DriverFunctionFragment extends Fragment {
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView=inflater.inflate( R.layout.fragment_driver_function, container, false );

           //点击推送任务
            View orderList = mView.findViewById(R.id.driver_order_list);
            orderList.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_orderList=new Intent( getActivity(), getOrderListActivity.class );
                    startActivity( intent_orderList );

                }
            });
            //已接单
            View orderUndo = mView.findViewById(R.id.driver_get_order);
            orderUndo.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_orderUndo=new Intent( getActivity(), getOrderListActivity.class );
                    intent_orderUndo.putExtra( "order_undo","1" );
                    startActivity( intent_orderUndo );

                }
            });
            //已完成
            View orderDone = mView.findViewById(R.id.driver_finish_order);
            orderDone.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_orderDone=new Intent( getActivity(), getOrderListActivity.class );
                    intent_orderDone.putExtra( "order_done","2" );
                    startActivity( intent_orderDone );

                }
            });

            //点击承运商管理
            View managerList = mView.findViewById(R.id.driver_manage_list);
            managerList.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_managerList=new Intent( getActivity(), getManageListActivity.class );
                    startActivity( intent_managerList );

                }
            });
            //点击管控线路所属承运商申请变更
            View managerChange = mView.findViewById(R.id.driver_manager_change);
            managerChange.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_managerChange=new Intent( getActivity(), routeChangeActivity.class );
                    startActivity( intent_managerChange );

                }
            });


        }
        return mView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) mView.getParent()).removeView( mView );
    }

}
