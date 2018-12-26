package demo.wanxiang.zzz.com.wxdriver.bean;

/**
 * Created by leovo on 2018-12-19.
 */

public class UploadImg_Bean {

    /**
     * success : true
     * code : null
     * msg : 文件上传成功
     * data : {"url":"201812/a0b71a1b99bc10d31ac7c061ae2b4d66.jpg"}
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
         * url : 201812/a0b71a1b99bc10d31ac7c061ae2b4d66.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url=url;
        }
    }
}
