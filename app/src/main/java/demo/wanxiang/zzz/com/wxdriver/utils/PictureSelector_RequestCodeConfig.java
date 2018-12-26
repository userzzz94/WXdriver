package demo.wanxiang.zzz.com.wxdriver.utils;


public class PictureSelector_RequestCodeConfig {
    /**
     * 这里 使用不同的请求码,用来在结果返回页面区分注册司机页面(不管哪个页面需要使用PictureSelector这个控件的) 是哪一个按钮点击的, 设置给被点击按钮所
     * 对应的图片框中
     */
    public static final int REGISTER_DRIVECARD = 200;
    public static final int REGISTER_LISENCEPHOTO = 201;
    public static final int REGISTER_IDCARD = 202;
    public static final int REGISTER_IDCARD_BACK = 203;


    // 签收时
    public static final int RECEIVE_PICTURE_1 = 300;
    public static final int RECEIVE_PICTURE_2 = 301;

    //拍照上传头像时的请求码
    public static final int HEAD_IMAGE_TAKE_PHOTO = 302;
    //从图库选择图片时的请求码
    public static final int HEAD_IMAGE_SELECT_PICTURE = 303;

}
