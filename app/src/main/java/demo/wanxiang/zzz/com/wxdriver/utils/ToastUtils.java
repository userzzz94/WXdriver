package demo.wanxiang.zzz.com.wxdriver.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by leovo on 2018/12/12.
 */

public class ToastUtils {
    private Toast mToast;
    private static ToastUtils mToastUtils;

    private ToastUtils(Context context) {
        mToast = Toast.makeText(context.getApplicationContext(), null, Toast.LENGTH_LONG);
    }

    public static synchronized ToastUtils getInstance(Context context) {
        if (null == mToastUtils) {
            mToastUtils = new ToastUtils(context);
        }
        return mToastUtils;
    }
    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showToast(int toastMsg) {
        mToast.setText(toastMsg);
        mToast.show();
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showToast(String toastMsg) {
        mToast.setText(toastMsg);
        mToast.show();
    }

    /**
     * 取消toast，在activity的destory方法中调用
     */
    public void destory() {
        if (null != mToast) {
            mToast.cancel();
            mToast = null;
        }
        mToastUtils = null;
    }
}
