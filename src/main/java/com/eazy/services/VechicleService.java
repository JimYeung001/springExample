package com.eazy.services;

import com.eazy.aspects.LogAspect;
import org.springframework.stereotype.Service;

@Service
public class VechicleService {
    public void playMusic() {
        System.out.println("Vehicle service called to play music");
    }

    @LogAspect
    public String callBreaker() {
        return "Car break called.";
    }

    public void moveVehicle() {
        throw new RuntimeException("Ops, something happened..");
    }
}
