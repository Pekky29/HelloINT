/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.helloint.etc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author jiraw
 */
public class ConnectionBuilder {

    public static Connection getConnection() throws ClassNotFoundException {
        Connection conn = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try {
            Properties prop = new Properties();
            InputStream input = classloader.getResourceAsStream("config.properties");
            prop.load(input);
            String DBIP = prop.getProperty("database");
            String USERNAME = prop.getProperty("dbuser");
            String PASSWORD = prop.getProperty("dbpassword");
            String PORT = prop.getProperty("dbport");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + DBIP + ":" + PORT + "/HelloWorld?zeroDateTimeBehavior=convertToNull", USERNAME, PASSWORD);
        } catch (SQLException | ClassCastException | IOException err) {
            System.err.println(err);
        }
        return conn;
    }
}