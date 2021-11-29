
public class Expression {
    private String op;
    private int leftValue;
    private int rightValue;
    private boolean isUnary;
    private Expression left;
    private Expression right;

    /**
     * Parametrized Constructor which takes left, op and right value.
     *
     * @param leftValue LHS of an Expression
     * @param op Operation to be Performed
     * @param rightValue RHS of an Expression
     */
    public Expression(int leftValue, String op, int rightValue) {
        if (!op.equals("+")
                && !op.equals("-")
                && !op.equals("*")
                && !op.equals("/")) {
            throw new IllegalArgumentException("Invalid operator: "
                    + op
                    + ".");
        }
        this.leftValue = leftValue;
        this.op = op;
        this.rightValue = rightValue;
    }

    /**
     * Parametrized Constructor which takes left and right value as a Expression Object.
     *
     * @param left Expression Object which signify the Left Value
     * @param op Operation to be performed
     * @param right Expression Object which signify the right value
     */
    public Expression(Expression left, String op, Expression right) {
        if (!op.equals("+")
                && !op.equals("-")
                && !op.equals("*")
                && !op.equals("/")) {
            throw new IllegalArgumentException("Invalid operator: "
                    + op
                    + ".");
        }
        if (left == null) {
            throw new IllegalArgumentException("left is null");
        }
        if (right == null) {
            throw new IllegalArgumentException("right is null");
        }
        this.left = left;
        this.op = op;
        this.right = right;
    }

    /**
     * Constructor which only take left value
     *
     * @param leftValue smallest possible expreesion i.e just a left value
     */
    public Expression(int leftValue) {
        this.leftValue = leftValue;
        isUnary = true;
    }

    /**
     * Evaluates the expression and returns the Integer Result.
     *
     * @return Evaluated Value of a Expression
     */
    public int Evaluate() {
        if (isUnary) {
            return leftValue;
        } else if (left != null
                && right != null) {
            switch (op) {
            case "+":
                return left.Evaluate()
                        + right.Evaluate();
            case "-":
                return left.Evaluate()
                        - right.Evaluate();
            case "*":
                return left.Evaluate()
                        * right.Evaluate();
            case "/":
                int rightEvaluatedValue = right.Evaluate();
                if (rightEvaluatedValue == 0) {
                    throw new ArithmeticException("Cannot divide by 0.");
                }
                return left.Evaluate()
                        / right.Evaluate();
            }
        } else if (left == null
                && right == null) {
            switch (op) {
            case "+":
                return leftValue
                        + rightValue;
            case "-":
                return leftValue
                        - rightValue;
            case "*":
                return leftValue
                        * rightValue;
            case "/":
                if (rightValue == 0) {
                    throw new ArithmeticException("Cannot divide by 0.");
                }
                return leftValue
                        / rightValue;
            }
        }

        // We should never get here
        System.out.println("Invalid state!");
        return 0;
    }

    /**
     * It displays the expression by adding the ( and other symbols.
     *
     * @return Converted Expression as a String
     */
    public String PrintString() {
        if (isUnary) {
            return Integer.toString(leftValue);
        } else if (left != null
                && right != null) {
            return "("
                    + left.PrintString()
                    + " "
                    + op
                    + " "
                    + right.PrintString()
                    + ")";
        } else if (left == null
                && right == null) {
            return "("
                    + leftValue
                    + " "
                    + op
                    + " "
                    + rightValue
                    + ")";
        } else {
            // We should never get here
            throw new IllegalStateException("Invalid state!");
        }
    }
/*
Remaining Functions below are getter's and setter's as the data member's were Private.
 */
    public boolean isUnary() {
        return isUnary;
    }

    public void setUnary(boolean unary) {
        isUnary = unary;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(int leftValue) {
        this.leftValue = leftValue;
    }

    public int getRightValue() {
        return rightValue;
    }

    public void setRightValue(int rightValue) {
        this.rightValue = rightValue;
    }
}
