package demo.wanxiang.zzz.com.wxdriver.bean;

import android.provider.Telephony;

import java.util.List;

/**
 * Created by leovo on 2018-12-19.
 */

public class ManageList_Bean {


    /**
     * success : true
     * code : null
     * msg : 查询成功
     * data : [{"searchValue":null,"params":{},"id":0,"userid":null,"carrierid":1,"createtime":null,"createuser":null,"carriers":{"searchValue":null,"params":{},"id":1,"name":"山东鼎软天下信息技术有限公司","address":"力高国际","legalperson":"张三","legalcard":"13477444","contacts":"李五","tel":"18755445572","type":"D","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"X"}},{"searchValue":null,"params":{},"id":0,"userid":null,"carrierid":2,"createtime":null,"createuser":null,"carriers":{"searchValue":null,"params":{},"id":2,"name":"山东鼎软11","address":"力高","legalperson":"拉拉","legalcard":"35878787877","contacts":"嗯嗯","tel":"15677878877","type":"D","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"W"}},{"searchValue":null,"params":{},"id":0,"userid":null,"carrierid":3,"createtime":null,"createuser":null,"carriers":{"searchValue":null,"params":{},"id":3,"name":"山东图标信息技术有限公司","address":"山东济南1","legalperson":"张三","legalcard":"123456789","contacts":"李四","tel":"18766116682","type":"S","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"W"}},{"searchValue":null,"params":{},"id":0,"userid":null,"carrierid":4,"createtime":null,"createuser":null,"carriers":{"searchValue":null,"params":{},"id":4,"name":"山东图标信息技术有限公司","address":"山东济南2","legalperson":"张三","legalcard":"123456789","contacts":"李四","tel":"18766116682","type":"S","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"W"}},{"searchValue":null,"params":{},"id":0,"userid":null,"carrierid":5,"createtime":null,"createuser":null,"carriers":{"searchValue":null,"params":{},"id":5,"name":"嘉宝乳业","address":"新疆维吾尔自治区","legalperson":"申过","legalcard":"370322199108204910","contacts":"申过","tel":"13335116075","type":"S","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"W"}},{"searchValue":null,"params":{},"id":0,"userid":null,"carrierid":7,"createtime":null,"createuser":null,"carriers":{"searchValue":null,"params":{},"id":7,"name":"迪奥","address":"加拿大","legalperson":"Paul","legalcard":"370322198912052356","contacts":"保罗","tel":"13856985632","type":"W","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"W"}},{"searchValue":null,"params":{},"id":0,"userid":null,"carrierid":8,"createtime":null,"createuser":null,"carriers":{"searchValue":null,"params":{},"id":8,"name":"测试承运商","address":"威海荣成","legalperson":"张明浩","legalcard":"3703221999102821456","contacts":"张三 ","tel":"13588954646","type":"D","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"W"}}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List <ManageList> data;

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

    public List <ManageList> getData() {
        return data;
    }

    public void setData(List <ManageList> data) {
        this.data=data;
    }

    public static class ManageList {
        /**
         * searchValue : null
         * params : {}
         * id : 0
         * userid : null
         * carrierid : 1
         * createtime : null
         * createuser : null
         * carriers : {"searchValue":null,"params":{},"id":1,"name":"山东鼎软天下信息技术有限公司","address":"力高国际","legalperson":"张三","legalcard":"13477444","contacts":"李五","tel":"18755445572","type":"D","state":1,"createtime":null,"createuser":null,"updatetime":null,"updateuser":null,"category":"X"}
         */

        private Object searchValue;
        private ParamsBean params;
        private int id;
        private Object userid;
        private int carrierid;
        private Object createtime;
        private Object createuser;
        private CarriersList carriers;

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

        public Object getUserid() {
            return userid;
        }

        public void setUserid(Object userid) {
            this.userid=userid;
        }

        public int getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(int carrierid) {
            this.carrierid=carrierid;
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

        public CarriersList getCarriers() {
            return carriers;
        }

        public void setCarriers(CarriersList carriers) {
            this.carriers=carriers;
        }

        public static class ParamsBean {
        }

        public static class CarriersList {
            /**
             * searchValue : null
             * params : {}
             * id : 1
             * name : 山东鼎软天下信息技术有限公司
             * address : 力高国际
             * legalperson : 张三
             * legalcard : 13477444
             * contacts : 李五
             * tel : 18755445572
             * type : D
             * state : 1
             * createtime : null
             * createuser : null
             * updatetime : null
             * updateuser : null
             * category : X
             */

            private Object searchValue;
            private ParamsBeanX params;
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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
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

            public static class ParamsBeanX {
            }
        }
    }
}
