# Carbon
A Tree-Walk Interpreter written in Java

## Grammar

### Expressions

- *Literals*: Numbers, Strings, Booleans and `nil`
- *Unary Expressions*: A prefix `!` to perform a logical not, and `-` to negate a number
- *Binary Expressions*: The infix arithmetic (`+`, `-`, `*`, `/`) and logic (`==`, `!=`, `<`, `<=`, `>`, `>=`) operators
- *Parentheses for grouping*

```
expression → literal
           | unary
           | binary
           | grouping

literal    → NUMBER | STRING | "true" | "false" | "nil"
grouping   → "(" expression ")"
unary      → ( "-" | "!" ) expression
binary     → expression operator expression
operator   → "==" | "!=" | "<" | "<=" | ">" | ">="
           | "+"  | "-"  | "*" | "/" ;
```
