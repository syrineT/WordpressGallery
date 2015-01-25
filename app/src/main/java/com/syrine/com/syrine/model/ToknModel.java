package com.syrine.com.syrine.model;

/**
 * Created by syrinetrabelsi on 25/01/15.
 */
public class ToknModel {

       private String access_token;

    private String token_type;

    private String blog_id;

    private String blog_url;

    private String scope;


    public String getAccess_token() {
        return access_token;
    }


    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }


    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }


    public String getBlog_id() {
        return blog_id;
    }


    public void setBlog_id(String blog_id) {
        this.blog_id = blog_id;
    }


    public String getBlog_url() {
        return blog_url;
    }


    public void setBlog_url(String blog_url) {
        this.blog_url = blog_url;
    }


    public String getScope() {
        return scope;
    }


    public void setScope(String scope) {
        this.scope = scope;
    }
}
