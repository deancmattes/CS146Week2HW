package edu.sjsu.assignment3;
import java.util.LinkedList;

public class Stack {
    LinkedList<Character> list;

    public Stack() {
        list = new LinkedList<Character>();
    }

    public void push(char c){
        list.addFirst(c);
    }

    public char peek(){
        return list.getFirst();
    }

    public void pop(){
        list.removeFirst();
    }
}