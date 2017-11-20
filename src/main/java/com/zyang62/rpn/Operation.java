package com.zyang62.rpn;

public class Operation {
    String operator;
    Double value;
    public Operation(String operator, Double value){
        this.operator = operator;
        this.value = value;
    }

    public String getReverseOperation() throws RpnCalculatorException{
        return operator;
    }
}
