package com.zyang62.rpn;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

public class RpnCalculatorTest {
    @Test
    public void testExample1()throws Exception{
        RpnCalculator rpnCalculator = new RpnCalculator();
        Stack<Double> valStack = new Stack<Double>();
        rpnCalculator.compute("5 2");
        valStack = rpnCalculator.getValStack();
        assertEquals(new Double(2),valStack.pop());
        assertEquals(new Double(5),valStack.pop());
    }

    @Test
    public void testExample2()throws Exception{
        RpnCalculator rpnCalculator = new RpnCalculator();
        Stack<Double> valStack = new Stack<Double>();
        rpnCalculator.compute("2 sqrt");
        valStack = rpnCalculator.getValStack();
        assertEquals(new Double(Math.sqrt(2)),valStack.pop());
    }

    @Test
    public void testExample3()throws Exception{
        RpnCalculator rpnCalculator = new RpnCalculator();
        Stack<Double> valStack = new Stack<Double>();
        rpnCalculator.compute("5 2 -");
        valStack = rpnCalculator.getValStack();
        assertEquals(new Double(3),valStack.peek());
        rpnCalculator.compute("3 -");
        assertEquals(new Double(0),valStack.pop());
    }

    @Test
    public void testExample4()throws Exception{
        RpnCalculator rpnCalculator = new RpnCalculator();
        Stack<Double> valStack = new Stack<Double>();
        rpnCalculator.compute("5 4 3 2");
        rpnCalculator.compute("undo undo *");
        valStack = rpnCalculator.getValStack();
        assertEquals(new Double(20),valStack.peek());
        rpnCalculator.compute("5 *");
        assertEquals(new Double(100),valStack.peek());
        rpnCalculator.compute("undo");
        assertEquals(new Double(5),valStack.pop());
        assertEquals(new Double(20),valStack.pop());
    }

    @Test
    public void testExample5()throws Exception{
        RpnCalculator rpnCalculator = new RpnCalculator();
        Stack<Double> valStack = new Stack<Double>();
        rpnCalculator.compute("7 12 2 /");
        valStack = rpnCalculator.getValStack();
        assertEquals(new Double(6),valStack.peek());
        rpnCalculator.compute("*");
        assertEquals(new Double(42),valStack.peek());
        rpnCalculator.compute("4 /");
        assertEquals(new Double(10.5),valStack.pop());
    }

    @Test
    public void testExample6()throws Exception{
        RpnCalculator rpnCalculator = new RpnCalculator();
        Stack<Double> valStack = new Stack<Double>();
        rpnCalculator.compute("1 2 3 4 5");
        rpnCalculator.compute("*");
        valStack = rpnCalculator.getValStack();
        assertEquals(new Double(20),valStack.peek());
        rpnCalculator.compute("clear 3 4 -");
        assertEquals(new Double(-1),valStack.pop());
    }

    @Test
    public void testExample7()throws Exception{
        RpnCalculator rpnCalculator = new RpnCalculator();
        Stack<Double> valStack = new Stack<Double>();
        rpnCalculator.compute("1 2 3 4 5");
        rpnCalculator.compute("* * * *");
        valStack = rpnCalculator.getValStack();
        assertEquals(new Double(120),valStack.pop());
    }

    @Test(expected=RpnCalculatorException.class)
    public void testExample8()throws Exception{
        RpnCalculator rpnCalculator = new RpnCalculator();
        Stack<Double> valStack = new Stack<Double>();
        rpnCalculator.compute("1 2 3 * 5 + * * 6 5");
    }

    @Test(expected=RpnCalculatorException.class)
    public void testUnsupportedDataFormat()throws Exception{
        RpnCalculator rpnCalculator = new RpnCalculator();
        Stack<Double> valStack = new Stack<Double>();
        rpnCalculator.compute("asd");
    }

    @Test
    public void testUndoOperation()throws Exception{
        RpnCalculator rpnCalculator = new RpnCalculator();
        Stack<Double> valStack = new Stack<Double>();
        rpnCalculator.compute("1 2 +");
        rpnCalculator.compute("undo");
        valStack = rpnCalculator.getValStack();
        assertEquals(new Double(2),valStack.peek());
        rpnCalculator.compute("4 -");
        assertEquals(new Double(-2),valStack.peek());
        rpnCalculator.compute("undo");
        assertEquals(new Double(4),valStack.peek());
        rpnCalculator.compute("8 /");
        assertEquals(new Double(0.5),valStack.peek());
        rpnCalculator.compute("undo");
        assertEquals(new Double(8),valStack.peek());
        rpnCalculator.compute("9");
        rpnCalculator.compute("sqrt");
        assertEquals(new Double(3),valStack.peek());
        rpnCalculator.compute("undo");
        assertEquals(new Double(9),valStack.peek());
        rpnCalculator.compute("10");
        rpnCalculator.compute("undo");
        assertEquals(new Double(9),valStack.peek());
    }
}