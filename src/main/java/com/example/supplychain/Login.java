package com.example.supplychain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public boolean customerLogin(String email, String password){
        String query = String.format("SELECT * FROM customers WHERE email = '%s' AND password = '%s' ",email,password);
        try{
            DatabaseConnection dbCon = new DatabaseConnection();
            ResultSet rs =  dbCon.getQueryTable(query);
            if(rs != null && rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        Login login = new Login();
        System.out.println(login.customerLogin("ritwik@gmail.com","123abc"));
    }
}
