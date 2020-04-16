package com.sourcecode.carbon;

public class AstPrinter implements Expression.Visitor<String> {

    @Override
    public String visitBinaryExpression(Expression.Binary expression) {
        return parenthesize(
                expression.operator.lexeme,
                expression.left,
                expression.right
        );
    }

    @Override
    public String visitGroupingExpression(Expression.Grouping expression) {
        return parenthesize("group", expression.expression);
    }

    @Override
    public String visitLiteralExpression(Expression.Literal expression) {
        if (expression.value == null) {
            return "nil";
        }

        return expression.value.toString();
    }

    @Override
    public String visitUnaryExpression(Expression.Unary expression) {
        return parenthesize(expression.operator.lexeme, expression.right);
    }

    private String parenthesize(String name, Expression... expressions) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("(").append(name);

        for (Expression expression: expressions) {
            stringBuilder.append(" ");
            stringBuilder.append(expression.accept(this));
        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    public String print(Expression expression) {
        return expression.accept(this);
    }
}
