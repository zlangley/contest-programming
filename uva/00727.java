import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        if (n == 0)
            return;

        scan.nextLine();
        StringBuilder infix = new StringBuilder("(");
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.length() == 0) {
                infix.append(')');
                System.out.println(infixToPostfix(infix.toString()));
                System.out.println();
                infix = new StringBuilder("(");
            } else
                infix.append(line.charAt(0));
        }
        System.out.println(infixToPostfix(infix.toString()));
    }

    // assumes infix is surrounded by parens
    public static String infixToPostfix(String infix) {
        Stack<Character> stack = new Stack<Character>();
        StringBuilder postfix = new StringBuilder();

        for (char c : infix.toCharArray()) {
            if (c == ')') {
                while (stack.peek() != '(')
                    postfix.append(stack.pop());
                stack.pop();
            } else if (c == '(') {
                stack.push('(');
            } else if (getPriorityOf(c) != -1) {
                while (!stack.empty() && getPriorityOf(stack.peek()) >= getPriorityOf(c))
                    postfix.append(stack.pop());
                stack.push(c);
            } else
                postfix.append(c);
        }
        return postfix.toString();
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
}
