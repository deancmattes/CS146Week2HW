package edu.sjsu.assignment3;

import static java.lang.Character.isLetter;

public class Main {

    public static void main(String[] args) {
        System.out.println(convertToPostfix("a*b/(c-d)"));
    }

    public static int priorityOf(char c){
        int priority = 0;
        switch (c)
        {
            case '^' :
                priority = 3;
            case '*' : case '/' :
                priority = 2;
            case '+' : case '-' :
                priority = 1;
        }
        return priority;
    }

    public static String convertToPostfix(String infix){
        Stack operatorStack = new Stack();
        String postfix = "";
        for (int i = 0; i < infix.length(); i++){
            while (infix.charAt(i) == ' '){i++;}
            char nextCharacter = infix.charAt(i);
            if (isLetter(nextCharacter)){
                postfix += Character.toString(nextCharacter);
            } else {
                switch (nextCharacter)
                {
                    case '^':
                        operatorStack.push(nextCharacter);
                        break;
                    case '+' : case '-' : case '*' : case '/' :
                        if (operatorStack.isEmpty()){
                            operatorStack.push(nextCharacter);
                        } else {
                            while (!operatorStack.isEmpty() && priorityOf(nextCharacter) <= priorityOf(operatorStack.peek())) {
                                postfix += Character.toString(operatorStack.peek());
                                operatorStack.pop();
                            }
                            operatorStack.push(nextCharacter);
                        }
                        break;
                    case '(' :
                        operatorStack.push(nextCharacter);
                        break;
                    case ')' :
                       char topOperator = operatorStack.peek();
                       while (topOperator != '('){
                           postfix += Character.toString(topOperator);
                           operatorStack.pop();
                           topOperator = operatorStack.peek();
                       }
                       operatorStack.pop();
                       break;
                    default :
                        break;
                }
            }
        }
        while (!operatorStack.isEmpty()){
            char topOperator = operatorStack.peek();
            postfix += Character.toString(topOperator);
            operatorStack.pop();
        }
        return postfix;
    }
}
