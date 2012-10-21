import java.util.*;

public class InfixToPostfix {
    public static String infixToPostfix(String infix) {
        Stack<Character> stack = new Stack<Character>();
        Scanner infixScanner = new Scanner(infix + ")");
        StringBuilder postfix = new StringBuilder();
        String token;

        stack.push('(');
        while ((token = infixScanner.findInLine("\\d+|[^\\s]")) != null) {
            char c = token.charAt(0);

            if (c == ')') {
                while (stack.peek() != '(') {
                    postfix.append(' ');
                    postfix.append(stack.pop());
                }
                stack.pop();
            } else if (c == '(') {
                stack.push('(');
            } else if (getPriorityOf(c) != -1) {
                while (!stack.empty() && getPriorityOf(stack.peek()) >= getPriorityOf(c)) {
                    postfix.append(' ');
                    postfix.append(stack.pop());
                }
                stack.push(c);
            } else {
                postfix.append(' ');
                postfix.append(token);
            }
        }
        return postfix.toString().trim();
    }

    private static int getPriorityOf(char op) {
        switch (op) {
            case '-': return 1;
            case '+': return 1;
            case '*': return 2;
            case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            System.out.println(infixToPostfix(scan.nextLine()));
        }
    }
}
