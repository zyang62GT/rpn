package com.zyang62.rpn;

import java.io.Console;

public class Main {

    public static void main(String[] args) throws RpnCalculatorException {
//        Console console = System.console();
//        if (console == null) {
//            System.err.println("No console.");
//            System.exit(1);
//        }
//        RpnCalculator rpnCalculator = new RpnCalculator();
//        System.out.println("Enter your expression, or 'exit' to quit");
//        while(true){
//            String inputString = console.readLine("RPN> ");
//            if ("exit".equals(inputString)) {
//                break;
//            }else{
//                try{
//                    rpnCalculator.compute(inputString);
//                }catch (RpnCalculatorException e){
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
        RpnCalculator rpnCalculator = new RpnCalculator();
        rpnCalculator.compute("7 5 3 -");
    }
}
