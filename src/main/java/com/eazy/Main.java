package com.eazy;

import com.eazy.config.ApplicationConfig;
import com.eazy.services.VechicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        var vehicleService = context.getBean(VechicleService.class);
        System.out.println(vehicleService.getClass());
//        vehicleService.playMusic();
        var breaker = vehicleService.callBreaker();
        System.out.println(breaker);
//        vehicleService.moveVehicle();

    }
}