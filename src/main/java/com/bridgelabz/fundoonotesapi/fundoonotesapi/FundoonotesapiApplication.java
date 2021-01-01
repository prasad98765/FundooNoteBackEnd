package com.bridgelabz.fundoonotesapi.fundoonotesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class FundoonotesapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundoonotesapiApplication.class, args);
        System.out.println(System.getenv("MY_SQL_URL"));
    }
}
