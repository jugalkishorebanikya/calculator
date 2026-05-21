package com.example.calculator.service;

import com.example.calculator.exception.CalculatorException;
import com.example.calculator.model.CalculationResponse;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public CalculationResponse add(double a, double b) {
        return new CalculationResponse(a, b, "add", a + b);
    }

    public CalculationResponse subtract(double a, double b) {
        return new CalculationResponse(a, b, "subtract", a - b);
    }

    public CalculationResponse multiply(double a, double b) {
        return new CalculationResponse(a, b, "multiply", a * b);
    }

    public CalculationResponse divide(double a, double b) {
        if (b == 0) {
            throw new CalculatorException("DIVISION_BY_ZERO", "Cannot divide by zero.");
        }
        return new CalculationResponse(a, b, "divide", a / b);
    }

    public CalculationResponse modulo(double a, double b) {
        if (b == 0) {
            throw new CalculatorException("MODULO_BY_ZERO", "Cannot compute modulo with divisor zero.");
        }
        return new CalculationResponse(a, b, "modulo", a % b);
    }

    public CalculationResponse power(double base, double exponent) {
        return new CalculationResponse(base, exponent, "power", Math.pow(base, exponent));
    }

    public CalculationResponse squareRoot(double a) {
        if (a < 0) {
            throw new CalculatorException("NEGATIVE_SQRT", "Cannot compute square root of a negative number.");
        }
        // Treat b = 0 (unused) for a unary operation; reuse response model with b = 0
        return new CalculationResponse(a, 0, "sqrt", Math.sqrt(a)) {
            @Override public double getB() { return a; }  // show 'a' for expression clarity
        };
    }
}
