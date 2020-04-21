package dahua_ds;

import java.util.*;

/**
 * REVERSED为特殊标记，当进行逆波兰转换的时候，作为栈底的特殊标记
 */
enum TokenType {
    OPERAND, PLUS, MINUS, MULTIPLY, DIVIDE, LEFT_BRA, RIGHT_BRA, REVERSED
}

/**
 * value只用于OPERAND，因为其他的Token都和TokenType是一一对应的关系
 * priority用于+-*÷(，这里(的优先级被设置为-1也是勉勉强强
 * 因为(刚入栈和(作为栈顶时的状况完全相反，(刚入栈时，要求优先级绝对大才能直接入栈，而(作为栈顶时则要求优先级绝对小才能使后来的OPERAND直接入栈
 * 因此在本例中(为-1，只能满足(作为栈顶时的情况
 * (刚入栈时则要被当作一种特殊情况单独处理
 */
class Token {
    TokenType type;
    double value = 0d; // only used for type = OPERAND
    int priority; // used type = + - * / (, +- is 1, */ is 2, ( is -1

    Token(TokenType type, int priority) {
        this.type = type;
        this.priority = priority;
    }

    @Override
    public String toString() {
        switch (type) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case MULTIPLY:
                return "*";
            case DIVIDE:
                return "/";
            case LEFT_BRA:
                return "(";
            case RIGHT_BRA:
                return ")";
            default:
                return String.format("%f", value);
        }
    }
}

public class Calculator {
    /**
     * Token解析
     * @param line
     * @return
     */
    private static List<Token> parse(String line) {
        ArrayList<Token> result = new ArrayList<>();
        int len = line.length();
        int i = 0;
        while (i < len) {
            switch (line.charAt(i)) {
                case '+':
                    result.add(new Token(TokenType.PLUS, 1));
                    break;
                case '-':
                    result.add(new Token(TokenType.MINUS, 1));
                    break;
                case '*':
                    result.add(new Token(TokenType.MULTIPLY, 2));
                    break;
                case '/':
                    result.add(new Token(TokenType.DIVIDE, 2));
                    break;
                case '(':
                    result.add(new Token(TokenType.LEFT_BRA, -1));
                    break;
                case ')':
                    result.add(new Token(TokenType.RIGHT_BRA, -1));
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    do {
                        sb.append(line.charAt(i));
                        ++i;
                    } while (i < len && !isOperator(line.charAt(i)));
                    Token token = new Token(TokenType.OPERAND, -1);
                    token.value = Double.parseDouble(sb.toString());
                    result.add(token);
                    continue;
            }
            ++i;
        }
        return result;
    }
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    /**
     * 当类型为操作数时，直接输出
     * 当类型为(时，直接入栈
     * 当类型为OPERAND时，比较其与栈顶的优先级，此时的OPERAND更大，直接入栈即可，否则，先执行循环进行pop，直到当前的OPREAND优先级更大
     * 当类型为)时，一直出栈，直到对应的(出栈
     * @param tokens
     * @return
     */
    private static List<Token> reverse(List<Token> tokens) {
        ArrayList<Token> reversedTokens = new ArrayList<>();
        Stack<Token> help = new Stack<>();
        help.push(new Token(TokenType.REVERSED, -1));
        int len = tokens.size();
        int i = 0;
        while (i < len) {
            Token currentToken = tokens.get(i);
            if (currentToken.type == TokenType.OPERAND) {
                reversedTokens.add(currentToken);
            }
            else {
                if (currentToken.type == TokenType.LEFT_BRA) {
                    help.push(currentToken);
                }
                else if (currentToken.type == TokenType.RIGHT_BRA) {
                    while (help.peek().type != TokenType.LEFT_BRA) {
                        reversedTokens.add(help.pop());
                    }
                    help.pop();
                }
                else {
                    // 使用类型为reversed的token，在这里就不用判断栈空不空了
                    while (currentToken.priority <= help.peek().priority) {
                        reversedTokens.add(help.pop());
                    }
                    help.push(currentToken);
                }
            }
            ++i;
        }
        while (help.peek().type != TokenType.REVERSED) {
            reversedTokens.add(help.pop());
        }
        return reversedTokens;
    }

    /**
     * 计算逆波兰表达式
     * 遇到操作数入栈
     * 遇到操作符从栈中拿出两个计算，再入栈
     * @param reversedTokens
     * @return
     */
    private static double calculate(List<Token> reversedTokens) {
        Stack<Double> help = new Stack<>();
        int i = 0;
        int len = reversedTokens.size();
        while (i < len) {
            Token currentToken = reversedTokens.get(i);
            if (currentToken.type != TokenType.OPERAND) {
                double o1 = help.pop();
                double o2 = help.pop();
                if (currentToken.type == TokenType.PLUS) {
                    help.push(o2+o1);
                }
                else if (currentToken.type == TokenType.MINUS) {
                    help.push(o2-o1);
                }
                else if (currentToken.type == TokenType.MULTIPLY) {
                    help.push(o2*o1);
                }
                else {
                    help.push(o2/o1);
                }
            }
            else {
                help.push(currentToken.value);
            }
            ++i;
        }
        return help.pop();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<Token> tokens = parse(line);
        System.out.println(tokens);
        List<Token> reversedTokens = reverse(tokens);
        System.out.println(reversedTokens);
        System.out.println(calculate(reversedTokens));
    }
}
