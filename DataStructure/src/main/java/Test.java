import stack.array.StackArray;
import tree.binary.BinaryTree;

public class Test {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(0);
        bt.add(new int[]{1, 3}, new char[]{'L', 'L'});
        bt.add(new int[]{2, 6, 10}, new char[]{'R', 'R', 'R'});
        bt.add(new int[]{2, 5, 8, 11, 13, 14}, new char[]{'R', 'L', 'L', 'L', 'R', 'R'});
        bt.add(new int[]{2, 6, 9, 12}, new char[]{'R', 'R', 'L', 'L'});
        bt.add(new int[]{1, 4, 7}, new char[]{'L', 'R', 'L'});


//       BinaryTree bt1 = new BinaryTree(new LinkedList<>(Arrays.asList(0,1,3,4,7,2,5,8,11,13,14,6,9,12,10)),
//               new LinkedList<>(Arrays.asList(3,1,7,4,0,11,13,14,8,5,2,12,9,6,10)));

//        System.out.println(bt.countLeaves());
//        System.out.println(bt.height());
//        System.out.println(bt.getDiameter());
//        bt.printRightBoundary();
//        bt.printLeftBoundary();
//        bt.printPreOrder();
//        bt.printPreOrderComplete();
//        System.out.println(bt.toParenthesizedString());
//        System.out.println(bt.isComplete());
//        System.out.println(bt.isSymmetric());
//        System.out.println(bt.toCanonicalParenthesisString());

//        BinaryTree bt1 = new BinaryTree(0);
//        bt1.add(new int[]{1, 3, 5}, new char[]{'L', 'L', 'R'});
//        bt1.add(new int[]{1, 3, 5}, new char[]{'R', 'R', 'L'});
//        bt1.printDuplicateSubtrees();

//        BinaryTree bt2 = new BinaryTree(1);
//        bt2.add(new int[]{ 2, 3 },new char[]{ 'L', 'L'});
//        bt2.add(new int[]{ 4, 5, 6, 8, 9 }, new char[]{ 'R', 'R', 'R', 'R', 'R' });
//        bt2.add(new int[]{ 4, 2, 3 }, new char[]{ 'R', 'L', 'L'});
//        bt2.add(new int[]{ 4, 5, 6, 7 }, new char[]{ 'R', 'R', 'L', 'L'});
//        bt2.add(new int[]{ 4, 5, 6, 8, 9 }, new char[]{ 'R', 'R', 'L', 'R', 'R'});
//        bt2.add(new int[]{ 4, 5, 6, 7 }, new char[]{ 'R', 'R', 'R', 'L'});
//        bt2.printDuplicateSubtrees();


//        BinaryTree exTree = new BinaryTree("23+4*");
//        exTree.printPostOrderExpression();
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

    public static int[] nextGreaterPosition(int[] vals){
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
