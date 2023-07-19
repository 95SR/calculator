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

    static int cal(int rightOperand, int leftOperand, char operator) {
        System.out.println(rightOperand + " " + leftOperand + " " + operator);
        int result = 0;
        switch (operator) {
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return result = leftOperand - rightOperand;
            case '*':
            
                return result = leftOperand * rightOperand;
                
            case '/':
                if (rightOperand == 0) {
                    return 0;
                }
                return result = leftOperand / rightOperand;
        }
        return result;
    }

    static int run(String toBeCalculated) {
        Stack<Integer> valStack = new Stack<Integer>();
        Stack<Character> operatorStack = new Stack<Character>();

        char ch;
        int val;
        int result;
        for (int i = 0; i < toBeCalculated.length(); i++) {
            ch = toBeCalculated.charAt(i);
            
            System.out.println("ch "+ ch);
            if (isNumber(ch)) {
                val = ch - '0';
                valStack.push(val);
                System.out.println("valstack LN61"+ Arrays.toString(valStack.toArray()));
            } else if (isOperator(ch)) {
                System.out.println("isOperator");
                if (!operatorStack.empty()) {
                    System.out.println("LN65 op stack not empty");
                    Character prevOperator = operatorStack.peek();
                    if (getPrecedence(ch) < getPrecedence(prevOperator)) {
                        
                        int rightOperand = valStack.peek();
                        valStack.pop();
                        int leftOperand = valStack.peek();
                        valStack.pop();
                        int evaluate = cal(rightOperand, leftOperand, prevOperator);
                        valStack.push(evaluate);
                        operatorStack.pop();
                         System.out.println("valstack LN75"+ Arrays.toString(valStack.toArray()));
                    } else if(getPrecedence(ch) > getPrecedence(prevOperator)) {
                        operatorStack.push(ch);
                        System.out.println(ch + '>' + prevOperator);
                         System.out.println("valstack "+ Arrays.toString(operatorStack.toArray()));
                    }
                } else {
                    operatorStack.push(ch);
                    System.out.println("valstack LN83 "+ Arrays.toString(operatorStack.toArray()));
                }
            } else if (ch == '(') {
                operatorStack.push(ch);
            } else if (ch == ')') {
                while (operatorStack.peek() != '(') {
                    char prevOperator = operatorStack.peek();
                    operatorStack.pop();
                    int rightOperand = valStack.peek();
                    valStack.pop();
                    int leftOperand = valStack.peek();
                    valStack.pop();
                    int evaluate = cal(rightOperand, leftOperand, prevOperator);
                    valStack.push(evaluate);

                }
                operatorStack.pop();
            }
        }

        System.out.println(valStack.size());
         System.out.println(valStack.peek());

        // while (valStack.size() > 1) {
        //     try {
        //         char operator = operatorStack.peek();
        //         operatorStack.pop();
        //         int rightOperand = valStack.peek();
        //         valStack.pop();
        //         int leftOperand = valStack.peek();
        //         valStack.pop();
        //         int evaluate = cal(rightOperand, leftOperand, operator);
        //         valStack.push(evaluate);
        //     } catch (EmptyStackException e) {
        //         System.out.println(e);
        //     }

        // }
        // result = valStack.peek();
        result = 0;
        return result;
    }

}
