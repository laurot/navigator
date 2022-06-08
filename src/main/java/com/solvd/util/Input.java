package com.solvd.util;

import java.util.Scanner;

public class Input {

    public final Scanner sc = new Scanner(System.in);

    private static final Input instance = new Input();

    private Input(){}

    public static Input getInput(){
        return instance;
    }
}