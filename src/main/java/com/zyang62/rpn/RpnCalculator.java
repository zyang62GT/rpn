package com.zyang62.rpn;

import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Stack;

public class RpnCalculator {
    private Stack<Double> valStack = new Stack<Double>();
    private Stack<Operation> operationStack = new Stack<Operation>();
    private int index = 0;

    public void compute(String expr) throws RpnCalculatorException{
        index = 0;
        for (String token : expr.split("\\s")) {
            index+=token.length()+1;
            computeToken(token);
        }
     }

    private void computeToken(String token) throws RpnCalculatorException{
        try{
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
                valStack.clear();
                operationStack.clear();
            }else if(token.toLowerCase().equals("undo")){
                undoOperation();
            }else if(token.equals("")||token.equals(" ")){

            }else{
                try {
                    operationStack.push(new Operation("push",Double.parseDouble(token)));
                    valStack.push(Double.parseDouble(token));
                }catch (Exception e){
                    throw new RpnCalculatorException("unsupported data format");
                }
            }
        }catch (EmptyStackException e){
            throw new RpnCalculatorException(String.format("operator %s (position: %d): insufficient parameters", token,index-1));
        }
    }

    private void undoOperation() throws RpnCalculatorException{
        Operation lastOperation = operationStack.pop();
        Double lastItem = valStack.pop();
        String token = lastOperation.operator;
        Double value = lastOperation.value;
        if(token.equals("+")){
            valStack.push(lastItem-value);
            valStack.push(value);
        }else if(token.equals("-")){
            valStack.push(lastItem+value);
            valStack.push(value);
        }else if(token.equals("/")){
            valStack.push(lastItem*value);
            valStack.push(value);
        }else if(token.equals("*")){
            valStack.push(lastItem/value);
            valStack.push(value);
        }else if(token.equals("sqrt")){
            valStack.push(lastItem*lastItem);
        }else if (token.equals("push")){

        }else{
            throw new RpnCalculatorException("unsupported operation");
        }
    }

     public Stack<Double> getValStack(){
        return valStack;
     }

}
