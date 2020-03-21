package com.ningbai.blog.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.id
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.account
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private String account;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.name
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.description
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.label
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private String label;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.sex
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private Integer sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.avatar_url
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private String avatarUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.gmt_create
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.gmt_modify
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private Long gmtModify;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.token
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column User.password
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.id
     *
     * @return the value of User.id
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.id
     *
     * @param id the value for User.id
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.account
     *
     * @return the value of User.account
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.account
     *
     * @param account the value for User.account
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.name
     *
     * @return the value of User.name
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.name
     *
     * @param name the value for User.name
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.description
     *
     * @return the value of User.description
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.description
     *
     * @param description the value for User.description
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.label
     *
     * @return the value of User.label
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public String getLabel() {
        return label;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.label
     *
     * @param label the value for User.label
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.sex
     *
     * @return the value of User.sex
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.sex
     *
     * @param sex the value for User.sex
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.avatar_url
     *
     * @return the value of User.avatar_url
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.avatar_url
     *
     * @param avatarUrl the value for User.avatar_url
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.gmt_create
     *
     * @return the value of User.gmt_create
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.gmt_create
     *
     * @param gmtCreate the value for User.gmt_create
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.gmt_modify
     *
     * @return the value of User.gmt_modify
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public Long getGmtModify() {
        return gmtModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.gmt_modify
     *
     * @param gmtModify the value for User.gmt_modify
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setGmtModify(Long gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.token
     *
     * @return the value of User.token
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.token
     *
     * @param token the value for User.token
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column User.password
     *
     * @return the value of User.password
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column User.password
     *
     * @param password the value for User.password
     *
     * @mbg.generated Sat Mar 21 21:43:57 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}