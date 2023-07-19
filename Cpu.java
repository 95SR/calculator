import java.util.Stack;

public class Cpu {
    
    static void test(String infixString){
        System.out.println(infixString);
    }

    //method to turn infix to postfix
    
    static int Precedence(char ch){
        switch (ch){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    } 

    static String toPostfix(String infixString){
        String postfix = "";

        Stack stack = new Stack<Character>();

        for(int i = 0 ; i < infixString.length(); i++){
            int c = infixString.charAt(i);

            
            System.out.println(c);
        }
        return postfix;
    }

    static int calculate(String input){
        int output = 0;
        
        String postfix = Cpu.toPostfix(input);


        return output;
    }


    //method to calculate postfix
}
