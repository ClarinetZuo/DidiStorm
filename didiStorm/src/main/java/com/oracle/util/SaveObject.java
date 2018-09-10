package com.oracle.util;

import com.oracle.vo.Didi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Clarinet
 * @since JDK8
 *
 * JDBC的工具
 */
public class SaveObject {

    public void saveObject(Didi info) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.128.1:3306/test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull","root","root");
        PreparedStatement prep = conn.prepareStatement("INSERT INTO "+PropertyUtil.getProperty("mysql_table_name")+" VALUES(?,?,?,?,?,?,?,?)");
        prep.setString(1,info.getId());
        prep.setString(2,info.getPhoneNumber());
        prep.setString(3,info.getAddress());
        prep.setString(4,info.getTime());
        prep.setInt(5,info.getDistance());
        prep.setDouble(6,info.getPrice());
        prep.setInt(7,info.getOrderCount());
        prep.setDouble(8,info.getPriceCount());

        prep.executeUpdate();

        if(null!=conn){
            conn.close();
        }
        if(null!=prep){
            prep.close();
        }


    }
}
