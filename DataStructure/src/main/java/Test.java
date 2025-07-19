import Hash.HashTableChaining;
import stack.array.StackArray;

import java.util.Stack;


public class Test {
    public static void main(String[] args) {

        HashTableChaining contact = new HashTableChaining();
        contact.addNum("Abdullah", "01140541248");
        contact.addNum("Mom", "01115079524");
        contact.addNum("Dad", "01125242600");

        System.out.println(contact.getNum("Abdullah"));
        contact.removeNum("Abdullah");
        System.out.println(contact.getNum("Abdullah"));
        System.out.println(contact.getNum("Dad"));
        System.out.println(contact.getNum("dad"));
        contact.printAllNums();
        contact.printTableStats();


//        Trie trie = new Trie();
//        trie.insert("a");
//        trie.insert("ab");
//        trie.insert("abcd");
//        trie.insert("bcd");
//        trie.insert("xyz");
//        trie.insert("xyzw");
//        trie.insert("xyzww");
//
//        List<String> listOfWords = new ArrayList<>();
//        trie.getAll(listOfWords);
//        System.out.println(listOfWords);
//
//        listOfWords = new ArrayList<>();
//        trie.autoComplete("x",listOfWords);
//        System.out.println(listOfWords);
    }

    public static String reverseSubWord(String line){
        StackArray<Character> stack= new StackArray<>();
        StringBuilder result = new StringBuilder();

        line += ' ';
        int i = 0;
        while (i < line.length()){
            if(line.charAt(i) == ' '){
                while (!stack.isEmpty()) {
                    result.append(stack.pop());
                }
                result.append(' ');
            }else {
                stack.push(line.charAt(i));
            }
            i++;
        }
        return result.toString();
    }

    public static int reverseNumber(int number){
        StackArray<Integer> stack = new StackArray<>();

        while (number > 0){
            stack.push(number%10);
            number /= 10;
        }

        int tens = 1;
        while (!stack.isEmpty()){
            number += stack.pop() * tens;
            tens *= 10;
        }
        return number;
    }

    public static boolean validParentheses(String str) {
        StackArray<Character> stack = new StackArray<>();
        int i = 0;
        while (i < str.length()) {
            char ch = str.charAt(i);

            if (ch == '[' || ch == '{' || ch == '(')
                stack.push(ch);
            else if (stack.isEmpty() || stack.pop() != getParenthesesMatch(ch))
                return false;

            i++;
        }
        return true;
    }
    public static char getParenthesesMatch(char ch){
        if(ch == ')')
            return '(';
        if(ch == '}')
            return '{';
        if(ch == ']')
            return '[';
        return 0;
    }

    public static String asteroidCollision(int[] asteroids){
        StackArray<Integer> stackRes = new StackArray<>(asteroids.length);

        for (int currAsteroid : asteroids) {
            if (stackRes.isEmpty() || currAsteroid > 0) { // asteroids[i] > 0 no matter if peek is positive or negative no explode will happen any way.
                stackRes.push(currAsteroid);
            } else { // negative asteroids
                while (true) {
                    int peek = stackRes.peek();
                    if (peek < 0) { // no explode as both peek and asteroid are negative
                        stackRes.push(currAsteroid);
                        break;
                    } else if (peek == -currAsteroid) { // explode will happen as peek equal negative asteroid
                        stackRes.pop();
                        break;
                    } else if (peek > -currAsteroid) {
                        break;
                    } else {
                        stackRes.pop();
                        if (stackRes.isEmpty()) {
                            stackRes.push(currAsteroid);
                            break;
                        }
                    }
                }
            }
        }
        return stackRes.toString();
    }

    public static int scoreOfParentheses(String str){ // String must have no spaces
        StackArray<Integer> stack = new StackArray<>(str.length());
        stack.push(0);

        for(int i = 0; i < str.length(); ++i){
            if(str.charAt(i) == '('){
                stack.push(0);
            }else {
                int last = stack.pop();

                if(last == 0){
                    last = 1;
                }else {
                    last *= 2;
                }
                stack.push((stack.pop() + last));
            }
        }
        return stack.pop();
    }

    //Monotonic Stack
    public static int[] nextGreaterPosition(int[] vals){
        Stack<Integer> stack = new Stack<>();
        StackArray<Integer> index = new StackArray<>(vals.length);
        int[] res = new int[vals.length];

        for(int i = 0; i < vals.length; ++i){
            while (!index.isEmpty() && vals[i] > vals[index.peek()])
                res[index.peek()] = i - index.pop();

            index.push(i);
        }
        return res;
    }

    public static String infixToPostfix(String infix) {     // Time complexity is O(n) not O(n^2)
        StackArray<Character> opStack = new StackArray<>(infix.length()+1);
        StringBuilder postfix = new StringBuilder();
        infix += '-';   // as it the least precedence it will pop all left operators in the stack.

        for (int i = 0; i < infix.length(); ++i){
            char curr = infix.charAt(i);
            if((curr >= '0' && curr <= '9') || (curr >= 'A' && curr <= 'Z') || (curr >= 'a' && curr <= 'z')){
                postfix.append(curr);
            }else if(curr == '+' || curr == '-' || curr == '*' || curr == '/' || curr == '^'){
                while (!opStack.isEmpty() &&
                        (precedence(opStack.peek()) >= precedence(curr) ||
                                (precedence(opStack.peek()) == precedence(curr) && curr != '^')))
                    postfix.append(opStack.pop());

                opStack.push(curr);
            } else if (curr == '(') {
                opStack.push(curr);

            } else if(curr == ')'){ //parentheses are equivalent to an independent expression
                while (!opStack.isEmpty() && opStack.peek() != '(' )
                    postfix.append(opStack.pop());

                opStack.pop();
            }
        }
        return postfix.toString();
    }
    private static int precedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    }

//    public static String infixToPrefix(String infix) {
//        StackArray<Character> opStack = new StackArray<>(infix.length()+1);
//        StringBuilder prefix = new StringBuilder();
//        infix += '-';
//
//    }

    public static double evaluatePostfix(String postFix) {
        StackArray<Double> stack = new StackArray<>(postFix.length());

        for (int i = 0; i < postFix.length(); ++i) {
            char curr = postFix.charAt(i);

            if (curr == '+' || curr == '-' || curr == '*' || curr == '/' || curr == '^') {
                stack.push(operations(curr, stack.pop(),stack.pop()));
            } else {
                stack.push((double) (curr - '0'));
            }
        }
        return stack.pop();
    }
    public static double operations(char operation, double operand1, double operand2 ){
        return switch (operation){
            case '+' -> operand2 + operand1;
            case '-' -> operand2 - operand1;
            case '*' -> operand2 * operand1;
            case '/' -> operand2 / operand1;
            default -> Math.pow(operand2,operand1);
        };
    }

}
