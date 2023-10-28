package com.maskun.moard;

import com.maskun.moard.web.controller.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class MoardApplication {


    public static void main(String[] args) {
        SpringApplication.run(MoardApplication.class, args);


    }

}
