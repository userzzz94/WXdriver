package demo.wanxiang.zzz.com.wxdriver;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import demo.wanxiang.zzz.com.wxdriver.main_driver_fragment.DriverCenterFragment;
import demo.wanxiang.zzz.com.wxdriver.main_driver_fragment.DriverFunctionFragment;
import demo.wanxiang.zzz.com.wxdriver.utils.ToastUtils;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener,View.OnClickListener {

    private static final String TAG = "MainActivity_Driver";
    //记录双击退出应用的第一次点击时间.
    private long firstTime = 0;

    private ViewPager pager;
    private List<Fragment> fragments;
    private RadioGroup radioGroup;
    private RadioButton function;
    private RadioButton center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initView();
    }

    private void initView() {
        pager=(ViewPager) findViewById( R.id.mian_driver_viewpager );
        radioGroup=(RadioGroup) findViewById( R.id.main_driver_radiogroup );
        function=findViewById( R.id.main_driver_function_radiobutton );
        function.setOnClickListener( this );
        center=findViewById( R.id.main_driver_center_radiobutton );
        center.setOnClickListener( this );
        fragments=new ArrayList <Fragment>();
        fragments.add( new DriverFunctionFragment());
        fragments.add( new DriverCenterFragment() );
        pager.setAdapter( new FragmentPagerAdapter( this.getSupportFragmentManager() ) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return fragments.get( arg0 );
            }
        } );
        // 添加页面切换事件的监听器
         pager.setOnPageChangeListener(this);
         radioGroup.check(R.id.main_driver_function_radiobutton);

        //设置加载时, drawtop图片无法设置大小的问题
        setDrawTopImageSize();
    }

    @Override
    public void finish() {
        ViewGroup view=(ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }

    /**
     * 这个方法在手指操作屏幕的时候发生变化。
     * 有三个值：0（END）,1(PRESS) , 2(UP) 。
     * 当用手指滑动翻页时，手指按下去的时候会触发这个方法，state值为1，手指抬起时，
     * 如果发生了滑动（即使很小），这个值会变为2，然后最后变为0
     * 总共执行这个方法三次。一种特殊情况是手指按下去以后一点滑动也没有发生，
     * 这个时候只会调用这个方法两次，state值分别是1,0 。
     * 当setCurrentItem翻页时，会执行这个方法两次，state值分别为2 , 0 。
     */
    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * 这个方法会在屏幕滚动过程中不断被调用。
     * 有三个参数，第一个position，这个参数要特别注意一下。
     * 当用手指滑动时，如果手指按在页面上不动，position和当前页面index是一致的；
     * 如果手指向左拖动（相应页面向右翻动），这时候position大部分时间和当前页面是一致的，
     * 只有翻页成功的情况下最后一次调用才会变为目标页面；如果手指向右拖动（相应页面向左翻动），
     * 这时候position大部分时间和目标页面是一致的，只有翻页不成功的情况下最后一次调用才会变为原页面。
     * 当直接设置setCurrentItem翻页时，如果是相邻的情况（比如现在是第二个页面，跳到第一或者第三个页面），
     * 如果页面向右翻动，大部分时间是和当前页面是一致的，只有最后才变成目标页面；
     * 如果向左翻动，position和目标页面是一致的。这和用手指拖动页面翻动是基本一致的。如果不是相邻的情况，
     * 比如我从第一个页面跳到第三个页面，position先是0，然后逐步变成1，然后逐步变成2；
     * 我从第三个页面跳到第一个页面，position先是1，然后逐步变成0，并没有出现为2的情况。
     * positionOffset是当前页面滑动比例，如果页面向右翻动，这个值不断变大，最后在趋近1的情况后突变为0
     * 如果页面向左翻动，这个值不断变小，
     * 最后变为0。positionOffsetPixels是当前页面滑动像素，变化情况和positionOffset一致
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     * 这个方法有一个参数position，代表哪个页面被选中。当用手指滑动翻页的时候，如果翻动成功了（滑动的距离够长），	 * 手指抬起来就会立即执行这个方法，position就是当前滑动到的页面。如果直接setCurrentItem翻页，	 * 那position就和setCurrentItem的参数一致，这种情况在onPageScrolled执行方法前就会立即执行。
     */
    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioGroup.check( R.id.main_driver_function_radiobutton );
                break;
            case 1:
                radioGroup.check( R.id.main_driver_center_radiobutton );
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_driver_center_radiobutton:
                pager.setCurrentItem( 1, true );
                break;
            case R.id.main_driver_function_radiobutton:
                pager.setCurrentItem( 0, true );
                break;
            default:
                break;
        }
    }

    /**
     * 设置页面加载时 drowTop的图片没法设置大小的问题
     */
    @SuppressLint("ResourceType")
    private void setDrawTopImageSize(){

        Drawable function_d = getResources().getDrawable(R.drawable.selector_driver_radiobutton_function);
        Drawable center_d = getResources().getDrawable(R.drawable .selector_driver_radiobutton_center);


        function_d.setBounds(0,0,70,70);
        center_d.setBounds(0,0,70,70);


        function.setCompoundDrawables(null,function_d,null,null);
        center.setCompoundDrawables(null,center_d,null,null);

    }

    /**
     * 双击退出程序 通过监听keyUp
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                ToastUtils.getInstance(MainActivity.this).showToast("再按一次退出程序");
                firstTime = secondTime;
                return true;
            } else {
                finish();
            }
        }

        return super.onKeyUp(keyCode, event);
    }


}
