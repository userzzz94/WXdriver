package demo.wanxiang.zzz.com.wxdriver.bean;

/**
 * Created by leovo on 2018-12-19.
 */

public class RegistInfoBean {

    /**
     * success : true
     * code : null
     * msg : 完善成功
     * data : null
     */

    private boolean success;
    private Object code;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data=data;
    }
}