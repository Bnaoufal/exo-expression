# exo-expression

Consider the Java resources.

The program is a simple expression evaluator able to compute integer
expressions based on four operators (add, subtract, multiply and divide). The
logic is implemented in class Expression, which offers the following features:
* Evaluating the expression to retrieve its Integer value
* Pretty printing the expression
*	Exporting the expression to XML.

The goal of this exercise is to refactor the Expression class (and optionally the
main program) to improve the code and to write unit tests to validate the program is functioning in the proper way.

You can change the code in any way you want, keeping in mind that:
* In the main program, we still want to create expression programmatically (no need to implement a parser)
* The 3 features described above must behave like in the original code, specifically:
* No matter how the API for constructing the expression is implemented, it must still take care of invalid input.
* The output of pretty-printing the expression must be the same as now.
* The output of XML serialization must be the same as now.

You can add classes and change the API as you wish, as long as the previous points are not violated.

What we’re looking for is clean, solid, easy to understand, and correct code. Also
think about which design pattern can help you make the code more readable and more testable. In essence this task will require some level of refactoring in order to be successfully carried out.
