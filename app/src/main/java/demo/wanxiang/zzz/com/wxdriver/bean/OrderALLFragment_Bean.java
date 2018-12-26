package demo.wanxiang.zzz.com.wxdriver.bean;

import java.util.List;

/**
 * Created by leovo on 2018-12-20.
 */

public class OrderALLFragment_Bean {


    /**
     * success : true
     * code : null
     * msg : 查询成功
     * data : [{"id":2,"userid":125,"driverid":127,"carrierid":3,"waybillid":2,"lineid":5,"isaccept":1,"accepttime":null,"canceltime":null,"state":1,"createtime":"2018-12-20 09:56:51","createuser":"125","updatetime":null,"updateuser":null,"licenseplate":"京A88888","line":{"searchValue":null,"params":{},"id":5,"origin":"济南码头","destination":"日照港","state":1,"createtime":null,"createuser":null,"flag":false},"user":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":127,"deptId":null,"parentId":null,"loginName":"18766116682","userName":"string","userType":null,"email":null,"phonenumber":"string","sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"loginIp":null,"loginDate":null,"dept":null,"roles":null,"roleIds":null,"postIds":null,"carrierid":null,"address":"string","accessoryphone":"string","idcard":"string","licenseplate":"京A88888","vehicletype":null,"auditstate":null,"audittime":null,"audituser":null,"carriers":null,"admin":false},"waybill":{"searchValue":null,"params":{},"id":2,"code":"154521727721990","planid":null,"carrierid":3,"planweight":10,"totalvehicle":0,"outsource":0,"state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"transporplan":{"id":null,"code":"1545134996328606","mainid":3,"makedate":"2018-12-19 00:00:00","type":"C","deliverunit":"山东鼎软天下信息技术有限公司","deliveraddress":"济南码头","delivercontact":"糍粑","delivertel":"13589536548","receiveunit":"滨州万象物流有限公司","receiveaddress":"日照港","receivecontact":"张三","receivetel":"158963","contractdate":"2018-12-19 00:00:00","customer":"申加名","material":"手机","totalweight":1200,"leadsealing":1,"businessnumber":"123213","remark":"1223454","planweight":10,"totalvehicle":0,"state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"maincontract":null},"carriers":{"searchValue":null,"params":{},"id":3,"name":"山东图标信息技术有限公司","address":"山东济南1","legalperson":"张三","legalcard":"123456789","contacts":"李四","tel":"18766116682","type":"S","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"W"}}}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List <OrderList> data;

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

    public List <OrderList> getData() {
        return data;
    }

    public void setData(List <OrderList> data) {
        this.data=data;
    }

    public static class OrderList {
        /**
         * id : 2
         * userid : 125
         * driverid : 127
         * carrierid : 3
         * waybillid : 2
         * lineid : 5
         * isaccept : 1
         * accepttime : null
         * canceltime : null
         * state : 1
         * createtime : 2018-12-20 09:56:51
         * createuser : 125
         * updatetime : null
         * updateuser : null
         * licenseplate : 京A88888
         * line : {"searchValue":null,"params":{},"id":5,"origin":"济南码头","destination":"日照港","state":1,"createtime":null,"createuser":null,"flag":false}
         * user : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":127,"deptId":null,"parentId":null,"loginName":"18766116682","userName":"string","userType":null,"email":null,"phonenumber":"string","sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"loginIp":null,"loginDate":null,"dept":null,"roles":null,"roleIds":null,"postIds":null,"carrierid":null,"address":"string","accessoryphone":"string","idcard":"string","licenseplate":"京A88888","vehicletype":null,"auditstate":null,"audittime":null,"audituser":null,"carriers":null,"admin":false}
         * waybill : {"searchValue":null,"params":{},"id":2,"code":"154521727721990","planid":null,"carrierid":3,"planweight":10,"totalvehicle":0,"outsource":0,"state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"transporplan":{"id":null,"code":"1545134996328606","mainid":3,"makedate":"2018-12-19 00:00:00","type":"C","deliverunit":"山东鼎软天下信息技术有限公司","deliveraddress":"济南码头","delivercontact":"糍粑","delivertel":"13589536548","receiveunit":"滨州万象物流有限公司","receiveaddress":"日照港","receivecontact":"张三","receivetel":"158963","contractdate":"2018-12-19 00:00:00","customer":"申加名","material":"手机","totalweight":1200,"leadsealing":1,"businessnumber":"123213","remark":"1223454","planweight":10,"totalvehicle":0,"state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"maincontract":null},"carriers":{"searchValue":null,"params":{},"id":3,"name":"山东图标信息技术有限公司","address":"山东济南1","legalperson":"张三","legalcard":"123456789","contacts":"李四","tel":"18766116682","type":"S","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"W"}}
         */

        private int id;
        private int userid;
        private int driverid;
        private int carrierid;
        private int waybillid;
        private int lineid;
        private int isaccept;
        private Object accepttime;
        private Object canceltime;
        private int state;
        private String createtime;
        private String createuser;
        private Object updatetime;
        private Object updateuser;
        private String licenseplate;
        private LineBean line;
        private UserBean user;
        private WaybillBean waybill;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id=id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid=userid;
        }

        public int getDriverid() {
            return driverid;
        }

        public void setDriverid(int driverid) {
            this.driverid=driverid;
        }

        public int getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(int carrierid) {
            this.carrierid=carrierid;
        }

        public int getWaybillid() {
            return waybillid;
        }

        public void setWaybillid(int waybillid) {
            this.waybillid=waybillid;
        }

        public int getLineid() {
            return lineid;
        }

        public void setLineid(int lineid) {
            this.lineid=lineid;
        }

        public int getIsaccept() {
            return isaccept;
        }

        public void setIsaccept(int isaccept) {
            this.isaccept=isaccept;
        }

        public Object getAccepttime() {
            return accepttime;
        }

        public void setAccepttime(Object accepttime) {
            this.accepttime=accepttime;
        }

        public Object getCanceltime() {
            return canceltime;
        }

        public void setCanceltime(Object canceltime) {
            this.canceltime=canceltime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state=state;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime=createtime;
        }

        public String getCreateuser() {
            return createuser;
        }

        public void setCreateuser(String createuser) {
            this.createuser=createuser;
        }

        public Object getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Object updatetime) {
            this.updatetime=updatetime;
        }

        public Object getUpdateuser() {
            return updateuser;
        }

        public void setUpdateuser(Object updateuser) {
            this.updateuser=updateuser;
        }

        public String getLicenseplate() {
            return licenseplate;
        }

        public void setLicenseplate(String licenseplate) {
            this.licenseplate=licenseplate;
        }

        public LineBean getLine() {
            return line;
        }

        public void setLine(LineBean line) {
            this.line=line;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user=user;
        }

        public WaybillBean getWaybill() {
            return waybill;
        }

        public void setWaybill(WaybillBean waybill) {
            this.waybill=waybill;
        }

        public static class LineBean {
            /**
             * searchValue : null
             * params : {}
             * id : 5
             * origin : 济南码头
             * destination : 日照港
             * state : 1
             * createtime : null
             * createuser : null
             * flag : false
             */

            private Object searchValue;
            private ParamsBean params;
            private int id;
            private String origin;
            private String destination;
            private int state;
            private Object createtime;
            private Object createuser;
            private boolean flag;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue=searchValue;
            }

            public ParamsBean getParams() {
                return params;
            }

            public void setParams(ParamsBean params) {
                this.params=params;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id=id;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin=origin;
            }

            public String getDestination() {
                return destination;
            }

            public void setDestination(String destination) {
                this.destination=destination;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state=state;
            }

            public Object getCreatetime() {
                return createtime;
            }

            public void setCreatetime(Object createtime) {
                this.createtime=createtime;
            }

            public Object getCreateuser() {
                return createuser;
            }

            public void setCreateuser(Object createuser) {
                this.createuser=createuser;
            }

            public boolean isFlag() {
                return flag;
            }

            public void setFlag(boolean flag) {
                this.flag=flag;
            }

            public static class ParamsBean {
            }
        }

        public static class UserBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * userId : 127
             * deptId : null
             * parentId : null
             * loginName : 18766116682
             * userName : string
             * userType : null
             * email : null
             * phonenumber : string
             * sex : null
             * avatar : null
             * password : null
             * salt : null
             * status : null
             * delFlag : null
             * loginIp : null
             * loginDate : null
             * dept : null
             * roles : null
             * roleIds : null
             * postIds : null
             * carrierid : null
             * address : string
             * accessoryphone : string
             * idcard : string
             * licenseplate : 京A88888
             * vehicletype : null
             * auditstate : null
             * audittime : null
             * audituser : null
             * carriers : null
             * admin : false
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBeanX params;
            private int userId;
            private Object deptId;
            private Object parentId;
            private String loginName;
            private String userName;
            private Object userType;
            private Object email;
            private String phonenumber;
            private Object sex;
            private Object avatar;
            private Object password;
            private Object salt;
            private Object status;
            private Object delFlag;
            private Object loginIp;
            private Object loginDate;
            private Object dept;
            private Object roles;
            private Object roleIds;
            private Object postIds;
            private Object carrierid;
            private String address;
            private String accessoryphone;
            private String idcard;
            private String licenseplate;
            private Object vehicletype;
            private Object auditstate;
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

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
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

            public Object getUserType() {
                return userType;
            }

            public void setUserType(Object userType) {
                this.userType=userType;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email=email;
            }

            public String getPhonenumber() {
                return phonenumber;
            }

            public void setPhonenumber(String phonenumber) {
                this.phonenumber=phonenumber;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex=sex;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
                this.avatar=avatar;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password=password;
            }

            public Object getSalt() {
                return salt;
            }

            public void setSalt(Object salt) {
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address=address;
            }

            public String getAccessoryphone() {
                return accessoryphone;
            }

            public void setAccessoryphone(String accessoryphone) {
                this.accessoryphone=accessoryphone;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard=idcard;
            }

            public String getLicenseplate() {
                return licenseplate;
            }

            public void setLicenseplate(String licenseplate) {
                this.licenseplate=licenseplate;
            }

            public Object getVehicletype() {
                return vehicletype;
            }

            public void setVehicletype(Object vehicletype) {
                this.vehicletype=vehicletype;
            }

            public Object getAuditstate() {
                return auditstate;
            }

            public void setAuditstate(Object auditstate) {
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

            public static class ParamsBeanX {
            }
        }

        public static class WaybillBean {
            /**
             * searchValue : null
             * params : {}
             * id : 2
             * code : 154521727721990
             * planid : null
             * carrierid : 3
             * planweight : 10.0
             * totalvehicle : 0
             * outsource : 0
             * state : 1
             * createtime : null
             * createuser : null
             * updatetime : null
             * updateuser : null
             * transporplan : {"id":null,"code":"1545134996328606","mainid":3,"makedate":"2018-12-19 00:00:00","type":"C","deliverunit":"山东鼎软天下信息技术有限公司","deliveraddress":"济南码头","delivercontact":"糍粑","delivertel":"13589536548","receiveunit":"滨州万象物流有限公司","receiveaddress":"日照港","receivecontact":"张三","receivetel":"158963","contractdate":"2018-12-19 00:00:00","customer":"申加名","material":"手机","totalweight":1200,"leadsealing":1,"businessnumber":"123213","remark":"1223454","planweight":10,"totalvehicle":0,"state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"maincontract":null}
             * carriers : {"searchValue":null,"params":{},"id":3,"name":"山东图标信息技术有限公司","address":"山东济南1","legalperson":"张三","legalcard":"123456789","contacts":"李四","tel":"18766116682","type":"S","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"W"}
             */

            private Object searchValue;
            private ParamsBeanXX params;
            private int id;
            private String code;
            private Object planid;
            private int carrierid;
            private double planweight;
            private int totalvehicle;
            private int outsource;
            private int state;
            private Object createtime;
            private Object createuser;
            private Object updatetime;
            private Object updateuser;
            private TransporplanBean transporplan;
            private CarriersBean carriers;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue=searchValue;
            }

            public ParamsBeanXX getParams() {
                return params;
            }

            public void setParams(ParamsBeanXX params) {
                this.params=params;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id=id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code=code;
            }

            public Object getPlanid() {
                return planid;
            }

            public void setPlanid(Object planid) {
                this.planid=planid;
            }

            public int getCarrierid() {
                return carrierid;
            }

            public void setCarrierid(int carrierid) {
                this.carrierid=carrierid;
            }

            public double getPlanweight() {
                return planweight;
            }

            public void setPlanweight(double planweight) {
                this.planweight=planweight;
            }

            public int getTotalvehicle() {
                return totalvehicle;
            }

            public void setTotalvehicle(int totalvehicle) {
                this.totalvehicle=totalvehicle;
            }

            public int getOutsource() {
                return outsource;
            }

            public void setOutsource(int outsource) {
                this.outsource=outsource;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state=state;
            }

            public Object getCreatetime() {
                return createtime;
            }

            public void setCreatetime(Object createtime) {
                this.createtime=createtime;
            }

            public Object getCreateuser() {
                return createuser;
            }

            public void setCreateuser(Object createuser) {
                this.createuser=createuser;
            }

            public Object getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(Object updatetime) {
                this.updatetime=updatetime;
            }

            public Object getUpdateuser() {
                return updateuser;
            }

            public void setUpdateuser(Object updateuser) {
                this.updateuser=updateuser;
            }

            public TransporplanBean getTransporplan() {
                return transporplan;
            }

            public void setTransporplan(TransporplanBean transporplan) {
                this.transporplan=transporplan;
            }

            public CarriersBean getCarriers() {
                return carriers;
            }

            public void setCarriers(CarriersBean carriers) {
                this.carriers=carriers;
            }

            public static class ParamsBeanXX {
            }

            public static class TransporplanBean {
                /**
                 * id : null
                 * code : 1545134996328606
                 * mainid : 3
                 * makedate : 2018-12-19 00:00:00
                 * type : C
                 * deliverunit : 山东鼎软天下信息技术有限公司
                 * deliveraddress : 济南码头
                 * delivercontact : 糍粑
                 * delivertel : 13589536548
                 * receiveunit : 滨州万象物流有限公司
                 * receiveaddress : 日照港
                 * receivecontact : 张三
                 * receivetel : 158963
                 * contractdate : 2018-12-19 00:00:00
                 * customer : 申加名
                 * material : 手机
                 * totalweight : 1200.0
                 * leadsealing : 1
                 * businessnumber : 123213
                 * remark : 1223454
                 * planweight : 10.0
                 * totalvehicle : 0
                 * state : 1
                 * createtime : null
                 * createuser : null
                 * updatetime : null
                 * updateuser : null
                 * maincontract : null
                 */

                private Object id;
                private String code;
                private int mainid;
                private String makedate;
                private String type;
                private String deliverunit;
                private String deliveraddress;
                private String delivercontact;
                private String delivertel;
                private String receiveunit;
                private String receiveaddress;
                private String receivecontact;
                private String receivetel;
                private String contractdate;
                private String customer;
                private String material;
                private double totalweight;
                private int leadsealing;
                private String businessnumber;
                private String remark;
                private double planweight;
                private int totalvehicle;
                private int state;
                private Object createtime;
                private Object createuser;
                private Object updatetime;
                private Object updateuser;
                private Object maincontract;

                public Object getId() {
                    return id;
                }

                public void setId(Object id) {
                    this.id=id;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code=code;
                }

                public int getMainid() {
                    return mainid;
                }

                public void setMainid(int mainid) {
                    this.mainid=mainid;
                }

                public String getMakedate() {
                    return makedate;
                }

                public void setMakedate(String makedate) {
                    this.makedate=makedate;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type=type;
                }

                public String getDeliverunit() {
                    return deliverunit;
                }

                public void setDeliverunit(String deliverunit) {
                    this.deliverunit=deliverunit;
                }

                public String getDeliveraddress() {
                    return deliveraddress;
                }

                public void setDeliveraddress(String deliveraddress) {
                    this.deliveraddress=deliveraddress;
                }

                public String getDelivercontact() {
                    return delivercontact;
                }

                public void setDelivercontact(String delivercontact) {
                    this.delivercontact=delivercontact;
                }

                public String getDelivertel() {
                    return delivertel;
                }

                public void setDelivertel(String delivertel) {
                    this.delivertel=delivertel;
                }

                public String getReceiveunit() {
                    return receiveunit;
                }

                public void setReceiveunit(String receiveunit) {
                    this.receiveunit=receiveunit;
                }

                public String getReceiveaddress() {
                    return receiveaddress;
                }

                public void setReceiveaddress(String receiveaddress) {
                    this.receiveaddress=receiveaddress;
                }

                public String getReceivecontact() {
                    return receivecontact;
                }

                public void setReceivecontact(String receivecontact) {
                    this.receivecontact=receivecontact;
                }

                public String getReceivetel() {
                    return receivetel;
                }

                public void setReceivetel(String receivetel) {
                    this.receivetel=receivetel;
                }

                public String getContractdate() {
                    return contractdate;
                }

                public void setContractdate(String contractdate) {
                    this.contractdate=contractdate;
                }

                public String getCustomer() {
                    return customer;
                }

                public void setCustomer(String customer) {
                    this.customer=customer;
                }

                public String getMaterial() {
                    return material;
                }

                public void setMaterial(String material) {
                    this.material=material;
                }

                public double getTotalweight() {
                    return totalweight;
                }

                public void setTotalweight(double totalweight) {
                    this.totalweight=totalweight;
                }

                public int getLeadsealing() {
                    return leadsealing;
                }

                public void setLeadsealing(int leadsealing) {
                    this.leadsealing=leadsealing;
                }

                public String getBusinessnumber() {
                    return businessnumber;
                }

                public void setBusinessnumber(String businessnumber) {
                    this.businessnumber=businessnumber;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark=remark;
                }

                public double getPlanweight() {
                    return planweight;
                }

                public void setPlanweight(double planweight) {
                    this.planweight=planweight;
                }

                public int getTotalvehicle() {
                    return totalvehicle;
                }

                public void setTotalvehicle(int totalvehicle) {
                    this.totalvehicle=totalvehicle;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state=state;
                }

                public Object getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(Object createtime) {
                    this.createtime=createtime;
                }

                public Object getCreateuser() {
                    return createuser;
                }

                public void setCreateuser(Object createuser) {
                    this.createuser=createuser;
                }

                public Object getUpdatetime() {
                    return updatetime;
                }

                public void setUpdatetime(Object updatetime) {
                    this.updatetime=updatetime;
                }

                public Object getUpdateuser() {
                    return updateuser;
                }

                public void setUpdateuser(Object updateuser) {
                    this.updateuser=updateuser;
                }

                public Object getMaincontract() {
                    return maincontract;
                }

                public void setMaincontract(Object maincontract) {
                    this.maincontract=maincontract;
                }
            }

            public static class CarriersBean {
                /**
                 * searchValue : null
                 * params : {}
                 * id : 3
                 * name : 山东图标信息技术有限公司
                 * address : 山东济南1
                 * legalperson : 张三
                 * legalcard : 123456789
                 * contacts : 李四
                 * tel : 18766116682
                 * type : S
                 * state : 1
                 * createtime : null
                 * createuser : null
                 * updatetime : null
                 * updateuser : null
                 * category : W
                 */

                private Object searchValue;
                private ParamsBeanXXX params;
                private int id;
                private String name;
                private String address;
                private String legalperson;
                private String legalcard;
                private String contacts;
                private String tel;
                private String type;
                private int state;
                private Object createtime;
                private Object createuser;
                private Object updatetime;
                private Object updateuser;
                private String category;

                public Object getSearchValue() {
                    return searchValue;
                }

                public void setSearchValue(Object searchValue) {
                    this.searchValue=searchValue;
                }

                public ParamsBeanXXX getParams() {
                    return params;
                }

                public void setParams(ParamsBeanXXX params) {
                    this.params=params;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id=id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name=name;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address=address;
                }

                public String getLegalperson() {
                    return legalperson;
                }

                public void setLegalperson(String legalperson) {
                    this.legalperson=legalperson;
                }

                public String getLegalcard() {
                    return legalcard;
                }

                public void setLegalcard(String legalcard) {
                    this.legalcard=legalcard;
                }

                public String getContacts() {
                    return contacts;
                }

                public void setContacts(String contacts) {
                    this.contacts=contacts;
                }

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel=tel;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type=type;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state=state;
                }

                public Object getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(Object createtime) {
                    this.createtime=createtime;
                }

                public Object getCreateuser() {
                    return createuser;
                }

                public void setCreateuser(Object createuser) {
                    this.createuser=createuser;
                }

                public Object getUpdatetime() {
                    return updatetime;
                }

                public void setUpdatetime(Object updatetime) {
                    this.updatetime=updatetime;
                }

                public Object getUpdateuser() {
                    return updateuser;
                }

                public void setUpdateuser(Object updateuser) {
                    this.updateuser=updateuser;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category=category;
                }

                public static class ParamsBeanXXX {
                }
            }
        }
    }
}
