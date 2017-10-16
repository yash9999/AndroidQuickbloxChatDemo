package com.alam.quickbloxchatdemo;

import java.util.ArrayList;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class UserImage {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private ArrayList<Datum> data = new ArrayList<Datum>();

    /**
     *
     * @return
     * The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     *
     * @param msg
     * The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     * @return
     * The statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @param statusCode
     * The statusCode
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     *
     * @return
     * The data
     */
    public ArrayList<Datum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }



    public class Datum {

        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("name")
        @Expose
        private String name;

        /**
         *
         * @return
         * The image
         */
        public String getImage() {
            return image;
        }

        /**
         *
         * @param image
         * The image
         */
        public void setImage(String image) {
            this.image = image;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }

    }
}
