package com.grouper.grouper_utility_control;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GrouperUtility {

    public static String generateUsername(String name){
        long generatedNumber = (long) Math.floor(Math.random() * 1_000_000_000);
        return name + generatedNumber;
    }

    public static long generateCode(){
        return (long) Math.floor(Math.random() * 1_00_000);

    }
}
