/**
 * Following the Factory Design Pattern we are creating an Expression Factory which will be responsible
 * for creating the object and returning it.
 */
public class ExpressionFactory {

    public static Expression getInstance(int left, String op, int right) {
       return new Expression(left,op,right);
    }

    public static Expression getComplexExpression(Expression left, String op, Expression right) {
        return new Expression(left,op,right);
    }
    public static Expression getUnaryExpression(int left) {
        return new Expression(left);
    }
}
