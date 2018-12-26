package demo.wanxiang.zzz.com.wxdriver.bean;

/**
 * Created by leovo on 2018-12-19.
 */

public class Login_Bean {


    /**
     * success : true
     * code : null
     * msg : 登录成功
     * data : {"id":132,"loginname":"13287578660","password":"666666","verification":null,"username":"13287578660","phonenumber":"","idcard":null,"auditstate":1,"address":null,"accessoryphone":null,"licenseplate":null,"filepath":null}
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
         * id : 132
         * loginname : 13287578660
         * password : 666666
         * verification : null
         * username : 13287578660
         * phonenumber :
         * idcard : null
         * auditstate : 1
         * address : null
         * accessoryphone : null
         * licenseplate : null
         * filepath : null
         */

        private int id;
        private String loginname;
        private String password;
        private Object verification;
        private String username;
        private String phonenumber;
        private Object idcard;
        private int auditstate;
        private Object address;
        private Object accessoryphone;
        private Object licenseplate;
        private Object filepath;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id=id;
        }

        public String getLoginname() {
            return loginname;
        }

        public void setLoginname(String loginname) {
            this.loginname=loginname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password=password;
        }

        public Object getVerification() {
            return verification;
        }

        public void setVerification(Object verification) {
            this.verification=verification;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username=username;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber=phonenumber;
        }

        public Object getIdcard() {
            return idcard;
        }

        public void setIdcard(Object idcard) {
            this.idcard=idcard;
        }

        public int getAuditstate() {
            return auditstate;
        }

        public void setAuditstate(int auditstate) {
            this.auditstate=auditstate;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address=address;
        }

        public Object getAccessoryphone() {
            return accessoryphone;
        }

        public void setAccessoryphone(Object accessoryphone) {
            this.accessoryphone=accessoryphone;
        }

        public Object getLicenseplate() {
            return licenseplate;
        }

        public void setLicenseplate(Object licenseplate) {
            this.licenseplate=licenseplate;
        }

        public Object getFilepath() {
            return filepath;
        }

        public void setFilepath(Object filepath) {
            this.filepath=filepath;
        }
    }
}
