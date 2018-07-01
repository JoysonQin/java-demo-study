package com.example.demostudy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class CodeGenerator {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        Set<String> setS = new HashSet<>();
        Set<String> setR = new HashSet<>();
        for (int i = 0; i < 99999; i++) {
            UUID uuid = UUID.randomUUID();
            Integer hashCode = Math.abs(uuid.hashCode());
            Integer hashCodeS = Math.abs(uuid.toString().hashCode());
            int code =  hashCode % 99999;
            int codeS = hashCodeS % 99999;
            set.add(String.valueOf(code));
            setS.add(String.valueOf(codeS));

            Random random = new Random();
            int codeR = random.nextInt() % 99999;
            setR.add(String.valueOf(codeR));
//            System.out.println("hashcode = " + hashCode);
//            System.out.println("hashcodeS = " + hashCodeS);
//            System.out.println("code = " + code);
//            System.out.println("codes = " + codeS);
        }
        System.out.println("set length=" + set.size());
        System.out.println("sets length=" + setS.size());
        System.out.println("setR length=" + setR.size());
    }
}
