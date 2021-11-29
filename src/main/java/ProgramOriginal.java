import java.io.IOException;


public class ProgramOriginal {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         * We are Following Factory Design Pattern, in this we will not create the object in the code but
         * instead we have created a Expression Factory which will create the object for us and
         * return it's value.
         */
        Expression leftExp = ExpressionFactory.getInstance(5,"+",4);
        Expression unaryExp = ExpressionFactory.getUnaryExpression(6);
        Expression rightExp = ExpressionFactory.getInstance(3,"-",1);

        Expression mixE = ExpressionFactory.getComplexExpression(leftExp,"*",unaryExp);
        Expression expression = ExpressionFactory.getComplexExpression(mixE,"/",rightExp);


        try {
            System.out.println(expression.PrintString()
                    + " = "
                    + expression.Evaluate());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        if (args.length > 0) {
            System.out.println("Serializing XML to '"
                    + args[0]
                    + "' ...");
            XMLCreate.SerializeToXml(args[0],expression);
            boolean success = XMLCreate.SerializeToXml(args[0],expression);
            System.out
                    .println(success ? "Success!" : "An error occurred.");
        }

        System.out.println("Press Enter to exit.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
