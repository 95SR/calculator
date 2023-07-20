import java.io.IOException;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public class Calc {

    static int getPrecedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    static boolean isNumber(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
    }

    static double cal(double rightOperand, double leftOperand, char operator) throws ArithmeticException {
        // System.out.println(rightOperand + " " + leftOperand + " " + operator);
        double result = 0;
        switch (operator) {
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return result = leftOperand - rightOperand;
            case '*':

                return result = leftOperand * rightOperand;

            case '/':
                return result = leftOperand / rightOperand;
        }
        return result;
    }

    static double run(String toBeCalculated) {
        Stack<Double> valStack = new Stack<Double>();
        Stack<Character> operatorStack = new Stack<Character>();

        char ch;
        double val = 0; // used for number > 9
        double result;
        boolean isDecimal = false;
        boolean isNegative = false;
        int decimalCount = 1;
        for (int i = 0; i < toBeCalculated.length(); i++) {
            ch = toBeCalculated.charAt(i);

            if (isNumber(ch)) {
                if (val != 0) {
                    valStack.pop();
                }
                if (i > 0 && toBeCalculated.charAt(i - 1) == '.') {
                    isDecimal = true;
                }

                val = (val * 10 + ch - '0');
                valStack.push(val);

                if (isDecimal) {
                    decimalCount *= 10;
                }

                // System.out.println("valstack LN61"+ Arrays.toString(valStack.toArray()));
            } else if (isOperator(ch)) {
                // System.out.println("isOperator");
                // valStack.push(val);
                if (isDecimal) {
                    valStack.pop();
                    if (isNegative) {
                        val = val * -1;
                        isNegative = false;
                    }
                    valStack.push(val / decimalCount);
                    // System.out.println(Arrays.toString(valStack.toArray()));
                    isDecimal = false;
                    decimalCount = 1;
                } else if (isNegative && !isDecimal) {
                    valStack.pop();
                    System.out.println("DEBUG " + val*-1);
                    valStack.push(val * -1);
                    System.out.println("DEBUG 22 " + Arrays.toString(valStack.toArray()));
                    isNegative = false;
                }

                // if (isNegative && !isDecimal){
                // valStack.pop();
                // valStack.push(val*-1);
                // isNegative=false;
                // }

                if (i == 0 && ch == '-') {
                    isNegative = true;
                    // multiplier = -1;
                    continue;
                } else if (isOperator(toBeCalculated.charAt(i - 1)) || toBeCalculated.charAt(i - 1) == '(') {
                    if (ch == '-') {
                        isNegative = true;
                        // multiplier = -1;
                        continue;
                    } else if (ch == '+') { // 3*+1

                        // multiplier = 1;
                        continue;
                    }

                }

                val = 0;
                if (!operatorStack.empty()) {
                    // System.out.println("LN65 op stack not empty");
                    Character prevOperator = operatorStack.peek();
                    if (getPrecedence(ch) <= getPrecedence(prevOperator)) {

                        double rightOperand = valStack.peek();
                        valStack.pop();
                        double leftOperand = valStack.peek();
                        valStack.pop();
                        double evaluate = cal(rightOperand, leftOperand, prevOperator);
                        valStack.push(evaluate);
                        operatorStack.pop();
                        operatorStack.push(ch);
                        // System.out.println("valstack LN75"+ Arrays.toString(valStack.toArray()));
                    } else if (getPrecedence(ch) > getPrecedence(prevOperator)) {
                        operatorStack.push(ch);

                        // System.out.println("valstack "+ Arrays.toString(operatorStack.toArray()));
                    }
                } else {

                    operatorStack.push(ch);
                    // System.out.println("valstack LN83 "+
                    // Arrays.toString(operatorStack.toArray()));
                }

            } else if (ch == '(') {
                operatorStack.push(ch);
            } else if (ch == ')') {
                // valStack.push(val);
                if (isDecimal) {
                    valStack.pop();
                    if (isNegative) {
                        val = val * -1;
                        isNegative = false;
                    }
                    valStack.push(val / decimalCount);
                    // System.out.println(Arrays.toString(valStack.toArray()));
                    isDecimal = false;
                    decimalCount = 1;
                }
                while (operatorStack.peek() != '(') {
                    char prevOperator = operatorStack.peek();
                    operatorStack.pop();
                    double rightOperand = valStack.peek();
                    valStack.pop();
                    double leftOperand = valStack.peek();
                    valStack.pop();
                    double evaluate = cal(rightOperand, leftOperand, prevOperator);
                    valStack.push(evaluate);

                }
                operatorStack.pop();
            }
            System.out.println(Arrays.toString(operatorStack.toArray()));
            System.out.println(Arrays.toString(valStack.toArray()));
        }
        if (isDecimal) {
            valStack.pop();
            valStack.push(val / decimalCount);
            isDecimal = false;
            System.out.println(Arrays.toString(valStack.toArray()));
        }

        do {
            char operator = operatorStack.peek();
            operatorStack.pop();
            double rightOperand = valStack.peek();
            valStack.pop();
            double leftOperand = valStack.peek();
            valStack.pop();
            double evaluate = cal(rightOperand, leftOperand, operator);
            valStack.push(evaluate);
        } while (operatorStack.size() >= 1);

        result = valStack.peek();
        // result = 0;
        return result;
    }

}
