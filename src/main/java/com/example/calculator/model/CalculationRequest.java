package com.example.calculator.model;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Input payload for a binary calculator operation")
public class CalculationRequest {

    @NotNull(message = "Operand 'a' must not be null")
    @Schema(description = "First operand", example = "10.5")
    private Double a;

    @NotNull(message = "Operand 'b' must not be null")
    @Schema(description = "Second operand", example = "3.2")
    private Double b;

    public CalculationRequest() {}

    public CalculationRequest(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    public Double getA() { return a; }
    public void setA(Double a) { this.a = a; }

    public Double getB() { return b; }
    public void setB(Double b) { this.b = b; }
}
