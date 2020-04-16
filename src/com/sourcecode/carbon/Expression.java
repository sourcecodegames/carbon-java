package com.sourcecode.carbon;

import java.util.List;

abstract class Expression {

    interface Visitor<R> {
        R visitBinaryExpression(Binary expression);
        R visitGroupingExpression(Grouping expression);
        R visitLiteralExpression(Literal expression);
        R visitUnaryExpression(Unary expression);
    }

    abstract <R> R accept(Visitor<R> visitor);

    public static class Binary extends Expression {

        final Expression left;
        final Token operator;
        final Expression right;

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBinaryExpression(this);
        }

        Binary(Expression left, Token operator, Expression right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }
    }

    public static class Grouping extends Expression {

        final Expression expression;

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitGroupingExpression(this);
        }

        Grouping(Expression expression) {
            this.expression = expression;
        }
    }

    public static class Literal extends Expression {

        final Object value;

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitLiteralExpression(this);
        }

        Literal(Object value) {
            this.value = value;
        }
    }

    public static class Unary extends Expression {

        final Token operator;
        final Expression right;

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitUnaryExpression(this);
        }

        Unary(Token operator, Expression right) {
            this.operator = operator;
            this.right = right;
        }
    }
}
