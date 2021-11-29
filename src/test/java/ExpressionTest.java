import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {

    @Test
    public void testSimpleConstructor() {
        Expression expression = new Expression(10,"+",5);
        assertEquals(10,expression.getLeftValue());
        assertEquals(5,expression.getRightValue());
        assertEquals("+",expression.getOp());

    }

    @Test
    void evaluateSimpleExpression() {
        Expression expression = new Expression(10,"+",5);
        int result =  expression.Evaluate();
        assertEquals(15,result);

        Expression expression2 = new Expression(10,"*",5);
        int result2 = expression2.Evaluate();
        assertEquals(50,result2);

        Expression expression3 = new Expression(10,"-",5);
        int result3 =  expression3.Evaluate();
        assertEquals(5,result3);

        Expression expression4 = new Expression(10,"/",5);
        int result4 = expression4.Evaluate();
        assertEquals(2,result4);

    }

    @Test
    void evaluateUnaryExpression() {
        Expression expression = new Expression(10);
        int result = expression.Evaluate();
        assertEquals(10,result);
    }

    @Test
    void evaluateComplexExpression() {
        Expression leftExp = new Expression(2,"+",4);
        Expression unaryExp = new Expression(6);
        Expression rightExp = new Expression(3,"-",2);

        Expression expression = new Expression(new Expression(
                leftExp, "*", unaryExp), "/",
                rightExp);

        int result = expression.Evaluate();
        assertEquals(36,result);
    }

    @Test
    void printString() {
        Expression expression = new Expression(10,"-",5);
        String st = expression.PrintString();
        assertEquals("(10 - 5)",st);

        Expression leftExp = new Expression(2,"+",4);
        Expression unaryExp = new Expression(6);
        Expression rightExp = new Expression(3,"-",2);

        Expression expression2 = new Expression(new Expression(
                leftExp, "*", unaryExp), "/",
                rightExp);

        String result = expression2.PrintString();
        assertEquals("(((2 + 4) * 6) / (3 - 2))",result);

    }
}