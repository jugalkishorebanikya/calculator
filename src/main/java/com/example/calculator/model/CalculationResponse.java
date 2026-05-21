package com.example.calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Result of a calculator operation")
public class CalculationResponse {

    @Schema(description = "First operand used", example = "10.5")
    private double a;

    @Schema(description = "Second operand used", example = "3.2")
    private double b;

    @Schema(description = "Operation performed", example = "add")
    private String operation;

    @Schema(description = "Result of the operation", example = "13.7")
    private double result;

    @Schema(description = "Human-readable expression", example = "10.5 + 3.2 = 13.7")
    private String expression;

    public CalculationResponse() {}

    public CalculationResponse(double a, double b, String operation, double result) {
        this.a = a;
        this.b = b;
        this.operation = operation;
        this.result = result;
        this.expression = buildExpression(a, b, operation, result);
    }

    private String buildExpression(double a, double b, String op, double res) {
        String symbol = switch (op) {
            case "add"      -> "+";
            case "subtract" -> "-";
            case "multiply" -> "×";
            case "divide"   -> "÷";
            case "modulo"   -> "%";
            case "power"    -> "^";
            default         -> op;
        };
        return String.format("%s %s %s = %s", format(a), symbol, format(b), format(res));
    }

    private String format(double v) {
        return v == Math.floor(v) && !Double.isInfinite(v)
               ? String.valueOf((long) v)
               : String.valueOf(v);
    }

    // Getters
    public double getA()          { return a; }
    public double getB()          { return b; }
    public String getOperation()  { return operation; }
    public double getResult()     { return result; }
    public String getExpression() { return expression; }
}
