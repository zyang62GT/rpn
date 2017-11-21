package com.zyang62.rpn;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws RpnCalculatorException {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(10);
        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        RpnCalculator rpnCalculator = new RpnCalculator();
        System.out.println("Enter RPN expression, or type 'exit' to quit the program");
        while(true){
            String inputString = console.readLine("RPN> ");
            if ("exit".equals(inputString)) {
                break;
            }else{
                try{
                    rpnCalculator.compute(inputString);
                }catch (RpnCalculatorException e){
                    System.out.println(e.getMessage());
                }
                Stack<Double> valStack = rpnCalculator.getValStack();
                System.out.print("Stack:");
                for(double num:valStack){
                    System.out.printf(" %s",df.format(num));
                }
                System.out.print("\n");
            }
        }
    }
}
