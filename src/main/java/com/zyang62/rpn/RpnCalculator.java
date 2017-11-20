package com.zyang62.rpn;

import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Stack;

public class RpnCalculator {
    private Stack<Double> valStack = new Stack<Double>();
    private Stack<Operation> operationStack = new Stack<Operation>();
    private DecimalFormat df = new DecimalFormat();

    RpnCalculator(){
        df.setMaximumFractionDigits(10);
    }

    public void compute(String expr) throws RpnCalculatorException{
        for (String token : expr.split("\\s+")) {
            if(token.equals("+")){
                operationStack.push(new Operation(token,valStack.peek()));
                valStack.push(valStack.pop() + valStack.pop());
            }else if(token.equals("-")){
                operationStack.push(new Operation(token,valStack.peek()));
                valStack.push(-valStack.pop() + valStack.pop());
            }else if(token.equals("*")){
                operationStack.push(new Operation(token,valStack.peek()));
                valStack.push(valStack.pop() * valStack.pop());
            }else if(token.equals("/")){
                operationStack.push(new Operation(token,valStack.peek()));
                double divisor = valStack.pop();
                valStack.push(valStack.pop() / divisor);
            }else if(token.toLowerCase().equals("sqrt")){
                operationStack.push(new Operation(token,valStack.peek()));
                valStack.push(Math.sqrt(valStack.pop()));
            }else if(token.toLowerCase().equals("clear")){
                operationStack.push(new Operation(token,valStack.peek()));
                valStack.clear();
            }else if(token.toLowerCase().equals("undo")){
                //valStack.pop();

            }else{
                operationStack.push(new Operation("push",valStack.peek()));
                valStack.push(Double.parseDouble(token));
            }
        }
        System.out.print("Stack:");
        for(double num:valStack){
            System.out.printf(" %s",df.format(num));
        }
        System.out.print("\n");
     }

     private void undoOperation(){

     }

     public Stack<Double> getValStack(){
        return valStack;
     }

}
