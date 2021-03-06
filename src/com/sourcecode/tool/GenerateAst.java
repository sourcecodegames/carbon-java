package com.sourcecode.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class GenerateAst {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: generate_ast <output_directory>");
            System.exit(1);
        }

        String outputDirectory = args[0];

        defineAst(outputDirectory, "Expression", Arrays.asList(
                "Binary   : Expression left, Token operator, Expression right",
                "Grouping : Expression expression",
                "Literal  : Object value",
                "Unary    : Token operator, Expression right"
        ));
    }

    private static void defineAst(
            String outputDirectory, String baseName, List<String> types)
            throws IOException {
        String path = outputDirectory + "/" + baseName + ".java";
        PrintWriter printWriter = new PrintWriter(path, StandardCharsets.UTF_8);

        printWriter.println("package com.sourcecode.carbon;");
        printWriter.println();

        printWriter.println("import java.util.List;");
        printWriter.println();

        printWriter.println("abstract class " + baseName + " {");

        printWriter.println();
        defineVisitor(printWriter, baseName, types);
        printWriter.println();

        // The base accept() method
        printWriter.println("    abstract <R> R accept(Visitor<R> visitor);");

        // Generate the AST classes
        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();

            printWriter.println();
            defineType(printWriter, baseName, className, fields);
        }

        printWriter.println("}");
        printWriter.close();
    }

    private static void defineVisitor(
            PrintWriter printWriter, String baseName, List<String> types) {
        printWriter.println("    interface Visitor<R> {");

        for (String type: types) {
            String typeName = type.split(":")[0].trim();
            printWriter.println("        R visit" + typeName + baseName + "(" +
                    typeName + " " + baseName.toLowerCase() + ");");
        }

        printWriter.println("    }");
    }

    private static void defineType(
            PrintWriter printWriter, String baseName,
            String className, String fieldList) {
        // Store parameters in fields array
        String[] fields = fieldList.split(", ");

        // Beginning of base class
        printWriter.println("    public static class " + className + " extends " +
                baseName + " {");

        // Fields
        printWriter.println();
        for (String field: fields) {
            printWriter.println("        final " + field + ";");
        }
        printWriter.println();

        // Visitor pattern
        printWriter.println("        @Override");
        printWriter.println("        <R> R accept(Visitor<R> visitor) {");
        printWriter.println("            return visitor.visit" + className + baseName + "(this);");
        printWriter.println("        }");
        printWriter.println();

        // Constructor
        printWriter.println("        " + className + "(" + fieldList + ") {");
        for (String field: fields) {
            String name = field.split(" ")[1];
            printWriter.println("            this." + name + " = " + name + ";");
        }
        printWriter.println("        }");

        // End of base class
        printWriter.println("    }");
    }
}
