package demo.wanxiang.zzz.com.wxdriver.bean;

/**
 * Created by leovo on 2018-12-19.
 */

public class GetVerificationRegister_Bean {

    /**
     * success : true
     * code : null
     * msg : 获取验证码成功
     * data : {"code":"663259"}
     */

    private boolean success;
    private Object code;
    private String msg;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success=success;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code=code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg=msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data=data;
    }

    public static class DataBean {
        /**
         * code : 663259
         */

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code=code;
        }
    }
}
