package com.loadbalancerproject.sampleapp.loadbalancersampleapp.config;


import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class DBConfig {
   public String driverClassName = "org.postgresql.Driver";
   public String url;
   public String user = "postgres";
   public String pass = "";

   public DBConfig(String driverClassName, String url, String user, String pass) {
      this.driverClassName = driverClassName;
      this.url = url;
      this.user = user;
      this.pass = pass;
   }
   public DBConfig(String url){
      this.url = url;
   }

   public String getDriverClassName() {
      return driverClassName;
   }

   public void setDriverClassName(String driverClassName) {
      this.driverClassName = driverClassName;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getPass() {
      return pass;
   }

   public void setPass(String pass) {
      this.pass = pass;
   }

   public DataSource toDataSource(){
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName(this.getDriverClassName());
      dataSource.setUrl(this.getUrl());
      dataSource.setUsername(this.getUser());
      dataSource.setPassword(this.getPass());

      return dataSource;
   }

}
