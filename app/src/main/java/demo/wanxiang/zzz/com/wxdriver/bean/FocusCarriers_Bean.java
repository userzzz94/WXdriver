package demo.wanxiang.zzz.com.wxdriver.bean;

/**
 * Created by leovo on 2018-12-20.
 */

public class FocusCarriers_Bean {

    /**
     * success : true
     * code : null
     * msg : 关注成功
     * data : {"searchValue":null,"params":{},"id":5,"userid":132,"carrierid":4,"createtime":"2018-12-20 09:57:01","createuser":"132","carriers":null}
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
         * searchValue : null
         * params : {}
         * id : 5
         * userid : 132
         * carrierid : 4
         * createtime : 2018-12-20 09:57:01
         * createuser : 132
         * carriers : null
         */

        private Object searchValue;

        private int id;
        private int userid;
        private int carrierid;
        private String createtime;
        private String createuser;
        private Object carriers;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue=searchValue;
        }

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

        public int getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(int carrierid) {
            this.carrierid=carrierid;
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

        public Object getCarriers() {
            return carriers;
        }

        public void setCarriers(Object carriers) {
            this.carriers=carriers;
        }

    }
}
