package com.example.calculator;

import com.example.calculator.exception.CalculatorException;
import com.example.calculator.model.CalculationResponse;
import com.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private CalculatorService service;

    @BeforeEach
    void setUp() {
        service = new CalculatorService();
    }

    // ── Addition ──────────────────────────────────────────────────

    @Test
    @DisplayName("Add: 3 + 4 = 7")
    void testAdd() {
        CalculationResponse res = service.add(3, 4);
        assertEquals(7.0, res.getResult());
        assertEquals("add", res.getOperation());
    }

    @Test
    @DisplayName("Add: negative numbers")
    void testAddNegative() {
        assertEquals(-1.0, service.add(-3, 2).getResult());
    }

    // ── Subtraction ───────────────────────────────────────────────

    @Test
    @DisplayName("Subtract: 10 - 3 = 7")
    void testSubtract() {
        assertEquals(7.0, service.subtract(10, 3).getResult());
    }

    // ── Multiplication ────────────────────────────────────────────

    @Test
    @DisplayName("Multiply: 6 × 7 = 42")
    void testMultiply() {
        assertEquals(42.0, service.multiply(6, 7).getResult());
    }

    @Test
    @DisplayName("Multiply: by zero yields 0")
    void testMultiplyByZero() {
        assertEquals(0.0, service.multiply(99, 0).getResult());
    }

    // ── Division ──────────────────────────────────────────────────

    @Test
    @DisplayName("Divide: 10 ÷ 4 = 2.5")
    void testDivide() {
        assertEquals(2.5, service.divide(10, 4).getResult());
    }

    @Test
    @DisplayName("Divide by zero throws CalculatorException")
    void testDivideByZero() {
        CalculatorException ex = assertThrows(
                CalculatorException.class,
                () -> service.divide(5, 0)
        );
        assertEquals("DIVISION_BY_ZERO", ex.getErrorCode());
    }

    // ── Modulo ────────────────────────────────────────────────────

    @Test
    @DisplayName("Modulo: 10 % 3 = 1")
    void testModulo() {
        assertEquals(1.0, service.modulo(10, 3).getResult());
    }

    @Test
    @DisplayName("Modulo by zero throws CalculatorException")
    void testModuloByZero() {
        assertThrows(CalculatorException.class, () -> service.modulo(5, 0));
    }

    // ── Power ─────────────────────────────────────────────────────

    @Test
    @DisplayName("Power: 2 ^ 10 = 1024")
    void testPower() {
        assertEquals(1024.0, service.power(2, 10).getResult());
    }

    @Test
    @DisplayName("Power: anything ^ 0 = 1")
    void testPowerZeroExponent() {
        assertEquals(1.0, service.power(99, 0).getResult());
    }

    // ── Square Root ───────────────────────────────────────────────

    @Test
    @DisplayName("Square root: √144 = 12")
    void testSqrt() {
        assertEquals(12.0, service.squareRoot(144).getResult());
    }

    @Test
    @DisplayName("Square root of negative throws CalculatorException")
    void testSqrtNegative() {
        CalculatorException ex = assertThrows(
                CalculatorException.class,
                () -> service.squareRoot(-9)
        );
        assertEquals("NEGATIVE_SQRT", ex.getErrorCode());
    }

    // ── Expression formatting ─────────────────────────────────────

    @Test
    @DisplayName("Expression string is populated correctly")
    void testExpression() {
        CalculationResponse res = service.add(5, 3);
        assertEquals("5 + 3 = 8", res.getExpression());
    }
}
