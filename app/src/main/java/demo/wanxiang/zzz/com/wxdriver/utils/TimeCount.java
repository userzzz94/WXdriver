package demo.wanxiang.zzz.com.wxdriver.utils;

import android.os.CountDownTimer;
import android.widget.Button;

import demo.wanxiang.zzz.com.wxdriver.R;

/**
 * Created by Administrator on 2018\5\30 0030.
 */

public class TimeCount extends CountDownTimer {
    private Button mBtnSendCode;
    public TimeCount(long millisInFuture, long countDownInterval, Button code) {
        super(millisInFuture, countDownInterval);
        mBtnSendCode = code;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        mBtnSendCode.setClickable(false);
        mBtnSendCode.setBackgroundResource(R.drawable.shape_dark_gray_gray);
        mBtnSendCode.setText(millisUntilFinished / 1000 + "秒");
    }

    @Override
    public void onFinish() {
        mBtnSendCode.setText("发送验证码");
        mBtnSendCode.setBackgroundResource(R.drawable.shape_blue_blue);
        mBtnSendCode.setClickable(true);
    }
}
