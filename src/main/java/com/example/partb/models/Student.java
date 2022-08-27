package com.example.partb.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Student {
    private String USN;
    private String Name;
    private String email;
    private String branch;

    @Override
    public String toString() {
        return "Student{" +
                "USN='" + USN + '\'' +
                ", Name='" + Name + '\'' +
                ", email='" + email + '\'' +
                ", branch='" + branch + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                '}';
    }

    private Character gender;
    private String password;
    private class metaData {
        public static final String tableName = "students";
        public static final String USN = "USN";
        public static final String name = "Name";
        public static final String email = "Email";
        public static final String branch = "Branch";
        public static final String password = "Password";
        public static final String gender = "Gender";
    }
    public String getUSN() {
        return USN;
    }
    public void setUSN(String USN) {
        this.USN = USN;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public Character getGender() {
        return gender;
    }
    public void setGender(Character gender) {
        this.gender = gender;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static void createTable(){
        String raw = "CREATE TABLE IF NOT EXISTS %s ( id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "%s VARCHAR(20),\n" +
                "%s VARCHAR(30),\n" +
                "%s VARCHAR(20),\n" +
                "%s VARCHAR(20),\n" +
                "%s  VARCHAR(20),\n" +
                "%s Char);";
        String query = String.format(raw, metaData.tableName, metaData.USN, metaData.name,metaData.password, metaData.email,metaData.branch,metaData.gender);
        Connection c = null;
        String url = "jdbc:sqlite:quiz.db";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(url);
            PreparedStatement ps = c.prepareStatement(query);
            boolean flag = ps.execute();
            System.out.println(flag);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
