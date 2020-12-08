package com.bridgelabz.fundoonotesapi.fundoonotesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class FundoonotesapiApplication {

    public static void main(String[] args) {

        SpringApplication.run(FundoonotesapiApplication.class, args);

//        String jdbcUrl = "jdbc:mysql://localhost:3306/fundoo_notes_database?useSSL=false";
//        String user = "root";
//        String pass = "prasad244965";
//        try {
//            System.out.println("connecting To database --------> "+jdbcUrl );
//
//            Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
//
//            System.out.println("Connection Done................... " + myConn );
//
//        }catch(Exception ex) {
//            ex.printStackTrace();
//        }
    }

}
