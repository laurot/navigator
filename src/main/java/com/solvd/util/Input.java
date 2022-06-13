package com.solvd.util;

import java.util.Scanner;

public class Input {

    private final Scanner sc = new Scanner(System.in);

    private static final Input instance = new Input();

    private Input(){}

    public static Input getInput(){
        return instance;
    }

    public int nextInt(){
        int a = sc.nextInt();
        sc.nextLine();
        return a;
    }

    public String nextLine(){
        String a = sc.nextLine();
        return a;
    }

    public void next(){
        sc.next();
    }
}