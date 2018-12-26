package demo.wanxiang.zzz.com.wxdriver.main_driver_fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

import demo.wanxiang.zzz.com.wxdriver.LoginActivity;
import demo.wanxiang.zzz.com.wxdriver.R;
import demo.wanxiang.zzz.com.wxdriver.center_activity.AboutActivity;
import demo.wanxiang.zzz.com.wxdriver.center_activity.ChangePwdActivity;
import demo.wanxiang.zzz.com.wxdriver.function_activity.getOrderListActivity;
import demo.wanxiang.zzz.com.wxdriver.function_activity.routeChangeActivity;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;
import demo.wanxiang.zzz.com.wxdriver.utils.UrlUtil;

/**
 * Created by leovo on 2018-12-14.
 */

public class DriverCenterFragment extends Fragment {
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView=inflater.inflate( R.layout.fragment_driver_center, container, false );

            //管控线路所属承运商申请变更
            View manageChange = mView.findViewById(R.id.driver_my_route_change);
            manageChange.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_manageChange=new Intent( getActivity(), routeChangeActivity.class );
                    startActivity( intent_manageChange );

                }
            });
            //修改密码
            View orderList = mView.findViewById(R.id.driver_my_change_pwd);
            orderList.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_orderList=new Intent( getActivity(), ChangePwdActivity.class );
                    startActivity( intent_orderList );

                }
            });
            //修改个人信息
            View myInfo = mView.findViewById(R.id.driver_my_change_info);
            myInfo.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_myInfo=new Intent( getActivity(), getOrderListActivity.class );
                    startActivity( intent_myInfo );

                }
            });
            // 关于
            View about = mView.findViewById(R.id.driver_my_about);
            about.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_about=new Intent( getActivity(), AboutActivity.class );
                    startActivity( intent_about );

                }
            });
            //退出登录
            View quit = mView.findViewById(R.id.driver_my_quit);
            quit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                   // request_quit();

                }
            });


        }
        return mView;
    }

    //退出登录检查司机状态，有任务不能退出
//    private void request_quit(){
//
//        String d_token=Hawk.get("t_driver");
//
//        if("".equals(driver .getId()) ){
//            Log.d("quit","null") ;
//        }else {
//            Log.d( "quit", driver .getId() );
//        }
//        OkGo.<String>get( UrlUtil.URL_Prefix + "Task/GetTc" )
//
//                .params("Token", d_token)
//                .params("Id",driver.getId())
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//
//                        Gson gson = new Gson();
//                        Driver_QuitBean bean = gson.fromJson(response.body(), Driver_QuitBean.class);
//                        String code = bean.getType();
//
//                        if("1".equals( code )){
//
//                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent intent = new Intent();
//                                    intent.setClass(getActivity(), LoginActivity.class);
//                                    getActivity().startActivity(intent);
//                                    getActivity().finish();
//                                }
//                            });
//                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();// 取消显示dialog
//                                }
//                            });
//                            builder.setMessage("确认要退出登录吗");
//                            builder.setTitle("退出登录");
//                            builder.show();
//
//                        } else {
//
//                            ToastUtils.getInstance(getActivity()).showToast(bean.getMessage());
//
//                        }
//
//
//                    }
//
//                    public void onError(Response<String> response) {
//
//                        super.onError(response);
//
//                        ToastUtils.getInstance(getActivity()).showToast("请求失败");
//                    }
//                });
//
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) mView.getParent()).removeView( mView );
    }
}
