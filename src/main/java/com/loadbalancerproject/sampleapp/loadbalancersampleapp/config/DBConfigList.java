package com.loadbalancerproject.sampleapp.loadbalancersampleapp.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;

public class DBConfigList {

    DBConfig base1 = new DBConfig("192.168.99.100:5432");
    DBConfig base2 = new DBConfig("192.168.99.100:5433");
    DBConfig base3 = new DBConfig("192.168.99.100:5434");
    DBConfig base4 = new DBConfig("192.168.99.100:5435");
    DBConfig base5 = new DBConfig("192.168.99.100:5436");
    DBConfig base6 = new DBConfig("192.168.99.100:5437");
    DBConfig base7 = new DBConfig("192.168.99.100:5438");
    DBConfig base8 = new DBConfig("192.168.99.100:5439");
    DBConfig base9 = new DBConfig("192.168.99.100:5440");
    DBConfig base10 = new DBConfig("192.168.99.100:5441");

    public ArrayList<DataSource> listDataSource(){
        ArrayList<DBConfig> listOfBases = new ArrayList<DBConfig>();
        listOfBases.add(base1);
        listOfBases.add(base2);
        listOfBases.add(base3);
        listOfBases.add(base4);
        listOfBases.add(base5);
        listOfBases.add(base6);
        listOfBases.add(base7);
        listOfBases.add(base8);
        listOfBases.add(base9);
        listOfBases.add(base10);

        ArrayList<DataSource> listOfDataSource = new ArrayList<DataSource>();
        for(int i = 0; i < 10; i++){
            listOfDataSource.add(listOfBases.get(i).toDataSource());
        }
        return listOfDataSource;
    }
}
