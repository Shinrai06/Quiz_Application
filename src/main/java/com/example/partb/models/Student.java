package com.example.partb.models;

import com.example.partb.exceptions.LoginException;

import java.sql.*;
import java.util.ArrayList;

public class Student {
    private String USN;
    private String Name;
    private String email;
    private String branch;
    private Character gender;
    private String password;
    public Student(){

    }
    public Student(String USN, String name, String email, String branch, Character gender, String password) {
        this.USN = USN;
        this.Name = name;
        this.email = email;
        this.branch = branch;
        this.gender = gender;
        this.password = password;
    }
    public Student(String email, String password) {
        this.email = email;
        this.password = password;
    }

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

    public class metaData {
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
    public boolean ifExists(String col,String value){
        String raw="SELECT * FROM %s WHERE %s = ?";
        String query = String.format(raw, metaData.tableName, col);
        String url = "jdbc:sqlite:quiz.db";
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection c = DriverManager.getConnection(url)){
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1,value);
                ResultSet set = ps.executeQuery();
                System.out.println("checking!!");
                if(set.next()){
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
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
    public Student save(){
        String raw="INSERT INTO %s (%s, %s, %s, %s,%s, %s)" + "Values (?,?,?,?,?,?);";
        String query = String.format(raw, metaData.tableName, metaData.USN, metaData.name,metaData.password, metaData.email,metaData.branch,metaData.gender);
        String url = "jdbc:sqlite:quiz.db";
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection c = DriverManager.getConnection(url)){
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1,this.USN);
                ps.setString(2,this.Name);
                ps.setString(3,this.password);
                ps.setString(4,this.email);
                ps.setString(5,this.branch);
                ps.setString(6,String.valueOf(this.gender));
                int i = ps.executeUpdate();
                System.out.println("updated row: "+ i);
                return this;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Student> getAll(){
        ArrayList<Student> students = new ArrayList<>();
        String raw="SELECT %s,%s,%s,%s,%s,%s FROM %s";
        String query = String.format(raw, metaData.USN,metaData.name,metaData.email,metaData.branch,metaData.password,metaData.gender,metaData.tableName);
        String url = "jdbc:sqlite:quiz.db";
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection c = DriverManager.getConnection(url)){
                PreparedStatement ps = c.prepareStatement(query);
                ResultSet keys = ps.executeQuery();
                System.out.println("Student Details: ");
                while(keys.next()){
                    Student s = new Student();
                    s.setUSN(keys.getString(1));
                    s.setName(keys.getString(2));
                    s.setEmail(keys.getString(3));
                    s.setBranch(keys.getString(4));
                    s.setPassword(keys.getString(5));
                    s.setGender(keys.getString(6).charAt(0));
                    students.add(s);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return students;
    }
    public void login() throws SQLException,ClassNotFoundException,LoginException{
        String raw="SELECT %s, %s, %s, %s FROM %s WHERE %s = ? AND %s = ?";
        String query = String.format(raw, metaData.USN, metaData.name, metaData.email, metaData.gender,
                metaData.tableName,metaData.email, metaData.password);
        System.out.println(query);
        String url = "jdbc:sqlite:quiz.db";
        Class.forName("org.sqlite.JDBC");
        try(Connection c = DriverManager.getConnection(url)){
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, this.email);
            ps.setString(2, this.password);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                this.setUSN(res.getString(1));
                this.setName(res.getString(2));
                this.setBranch(res.getString(3));
                this.setGender(res.getString(4).charAt(0));
            }else{
                throw new LoginException("Login Failed");
            }
        }
    }
}
