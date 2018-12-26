package demo.wanxiang.zzz.com.wxdriver.bean;

/**
 * Created by leovo on 2018-12-19.
 */

public class Register_Bean {


    /**
     * success : true
     * code : 200
     * msg : 注册成功
     * data : {"searchValue":null,"createBy":"13287578660","createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":132,"deptId":null,"parentId":null,"loginName":"13287578660","userName":"13287578660","userType":"S","email":null,"phonenumber":null,"sex":"2","avatar":null,"password":"0ea011ffddefadd17cfdc7ce1119ce51","salt":"0e5a29","status":null,"delFlag":null,"loginIp":null,"loginDate":null,"dept":null,"roles":null,"roleIds":null,"postIds":null,"carrierid":null,"address":null,"accessoryphone":null,"idcard":null,"licenseplate":null,"vehicletype":null,"auditstate":1,"audittime":null,"audituser":null,"carriers":null,"admin":false}
     */

    private boolean success;
    private int code;
    private String msg;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success=success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * searchValue : null
         * createBy : 13287578660
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * userId : 132
         * deptId : null
         * parentId : null
         * loginName : 13287578660
         * userName : 13287578660
         * userType : S
         * email : null
         * phonenumber : null
         * sex : 2
         * avatar : null
         * password : 0ea011ffddefadd17cfdc7ce1119ce51
         * salt : 0e5a29
         * status : null
         * delFlag : null
         * loginIp : null
         * loginDate : null
         * dept : null
         * roles : null
         * roleIds : null
         * postIds : null
         * carrierid : null
         * address : null
         * accessoryphone : null
         * idcard : null
         * licenseplate : null
         * vehicletype : null
         * auditstate : 1
         * audittime : null
         * audituser : null
         * carriers : null
         * admin : false
         */

        private Object searchValue;
        private String createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int userId;
        private Object deptId;
        private Object parentId;
        private String loginName;
        private String userName;
        private String userType;
        private Object email;
        private Object phonenumber;
        private String sex;
        private Object avatar;
        private String password;
        private String salt;
        private Object status;
        private Object delFlag;
        private Object loginIp;
        private Object loginDate;
        private Object dept;
        private Object roles;
        private Object roleIds;
        private Object postIds;
        private Object carrierid;
        private Object address;
        private Object accessoryphone;
        private Object idcard;
        private Object licenseplate;
        private Object vehicletype;
        private int auditstate;
        private Object audittime;
        private Object audituser;
        private Object carriers;
        private boolean admin;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue=searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy=createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime=createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy=updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime=updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark=remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params=params;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId=userId;
        }

        public Object getDeptId() {
            return deptId;
        }

        public void setDeptId(Object deptId) {
            this.deptId=deptId;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId=parentId;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName=loginName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName=userName;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType=userType;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email=email;
        }

        public Object getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(Object phonenumber) {
            this.phonenumber=phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex=sex;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar=avatar;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password=password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt=salt;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status=status;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag=delFlag;
        }

        public Object getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(Object loginIp) {
            this.loginIp=loginIp;
        }

        public Object getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(Object loginDate) {
            this.loginDate=loginDate;
        }

        public Object getDept() {
            return dept;
        }

        public void setDept(Object dept) {
            this.dept=dept;
        }

        public Object getRoles() {
            return roles;
        }

        public void setRoles(Object roles) {
            this.roles=roles;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds=roleIds;
        }

        public Object getPostIds() {
            return postIds;
        }

        public void setPostIds(Object postIds) {
            this.postIds=postIds;
        }

        public Object getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(Object carrierid) {
            this.carrierid=carrierid;
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

        public Object getIdcard() {
            return idcard;
        }

        public void setIdcard(Object idcard) {
            this.idcard=idcard;
        }

        public Object getLicenseplate() {
            return licenseplate;
        }

        public void setLicenseplate(Object licenseplate) {
            this.licenseplate=licenseplate;
        }

        public Object getVehicletype() {
            return vehicletype;
        }

        public void setVehicletype(Object vehicletype) {
            this.vehicletype=vehicletype;
        }

        public int getAuditstate() {
            return auditstate;
        }

        public void setAuditstate(int auditstate) {
            this.auditstate=auditstate;
        }

        public Object getAudittime() {
            return audittime;
        }

        public void setAudittime(Object audittime) {
            this.audittime=audittime;
        }

        public Object getAudituser() {
            return audituser;
        }

        public void setAudituser(Object audituser) {
            this.audituser=audituser;
        }

        public Object getCarriers() {
            return carriers;
        }

        public void setCarriers(Object carriers) {
            this.carriers=carriers;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin=admin;
        }

        public static class ParamsBean {
        }
    }
}
