package sample;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Calc {

    public double calculate(String s) {
        return countRPN(writeToRPN(s));
    }

    public boolean nextIsOperator(String next) {
        return (next.equals("+") || next.equals("-") || next.equals("*") || next
                .equals("/") || next.equals("^"));
    }

    private double countRPN(String s) {
        s = s.trim();
        String next;
        LinkedList<Double> stack = new LinkedList<>();

        Scanner scan = new Scanner(s);
        while (scan.hasNext()) {
            next = scan.next();
            if (nextIsOperator(next)) {
                if (stack.size() > 1) {
                    switch (next) {
                        case "+":
                            stack.push(stack.pop() + stack.pop());
                            break;
                        case "-":
                            stack.push(-stack.pop() + stack.pop());
                            break;
                        case "*":
                            stack.push(stack.pop() * stack.pop());
                            break;
                        case "^":

                            double head = stack.pop();
                            double tail = stack.pop();
                            stack.push(Math.pow(tail, head));
                            break;
                        case "/":

                            double first = stack.pop();
                            double second = stack.pop();
                            if (first == 0) {
                                System.out.println("Divide by zero.");
                            } else {
                                stack.push(second / first);
                            }
                            break;
                    }
                }
            } else {
                try {
                    if (next.equals("-")){
                        stack.push(-1.0);
                    }
                    stack.push(Double.parseDouble(next));
                } catch (NumberFormatException c) {
                    System.out.println("Wrong entry");
                }
            }
        }
        return stack.pop();
    }


    private String writeToRPN(String s) {
        if (s == null) {
            return "";
        }
        char[] chars = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder outStr = new StringBuilder();
        for (char aChar : chars)
            switch (aChar) {
                case '+':
                case '-':
                    while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/' || stack.peek() == '^')) {
                        outStr.append(' ').append(stack.pop());
                    }
                case '^':
                case '*':
                case '/':
                    outStr.append(' ');
                case '(':
                    stack.push(aChar);
                case ' ':
                    break;
                case ')':
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        outStr.append(' ').append(stack.pop());
                    }
                    if (!stack.isEmpty()) {
                        stack.pop();
                        break;
                    }
                default:
                    outStr.append(aChar);
                    break;
            }
        while (!stack.isEmpty())
            outStr.append(' ').append(stack.pop());
        return outStr.toString();
    }
}
