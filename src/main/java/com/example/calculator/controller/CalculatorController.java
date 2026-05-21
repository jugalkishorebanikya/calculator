package com.example.calculator.controller;

import com.example.calculator.model.CalculationRequest;
import com.example.calculator.model.CalculationResponse;
import com.example.calculator.service.CalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
@Validated
@Tag(name = "Calculator", description = "Basic arithmetic operations")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    // ──────────────────────────────────────────────────────────────
    // POST endpoints (request body)
    // ──────────────────────────────────────────────────────────────

    @PostMapping("/add")
    @Operation(summary = "Add two numbers")
    public ResponseEntity<CalculationResponse> add(@Valid @RequestBody CalculationRequest req) {
        return ResponseEntity.ok(calculatorService.add(req.getA(), req.getB()));
    }

    @PostMapping("/subtract")
    @Operation(summary = "Subtract b from a")
    public ResponseEntity<CalculationResponse> subtract(@Valid @RequestBody CalculationRequest req) {
        return ResponseEntity.ok(calculatorService.subtract(req.getA(), req.getB()));
    }

    @PostMapping("/multiply")
    @Operation(summary = "Multiply two numbers")
    public ResponseEntity<CalculationResponse> multiply(@Valid @RequestBody CalculationRequest req) {
        return ResponseEntity.ok(calculatorService.multiply(req.getA(), req.getB()));
    }

    @PostMapping("/divide")
    @Operation(summary = "Divide a by b")
    public ResponseEntity<CalculationResponse> divide(@Valid @RequestBody CalculationRequest req) {
        return ResponseEntity.ok(calculatorService.divide(req.getA(), req.getB()));
    }

    @PostMapping("/modulo")
    @Operation(summary = "Compute a modulo b")
    public ResponseEntity<CalculationResponse> modulo(@Valid @RequestBody CalculationRequest req) {
        return ResponseEntity.ok(calculatorService.modulo(req.getA(), req.getB()));
    }

    @PostMapping("/power")
    @Operation(summary = "Raise base (a) to the power of exponent (b)")
    public ResponseEntity<CalculationResponse> power(@Valid @RequestBody CalculationRequest req) {
        return ResponseEntity.ok(calculatorService.power(req.getA(), req.getB()));
    }

    // ──────────────────────────────────────────────────────────────
    // GET endpoints (query params) — handy for quick testing
    // ──────────────────────────────────────────────────────────────

    @GetMapping("/add")
    @Operation(summary = "Add two numbers (query params)")
    public ResponseEntity<CalculationResponse> addGet(
            @Parameter(description = "First operand")  @RequestParam @NotNull Double a,
            @Parameter(description = "Second operand") @RequestParam @NotNull Double b) {
        return ResponseEntity.ok(calculatorService.add(a, b));
    }

    @GetMapping("/subtract")
    @Operation(summary = "Subtract b from a (query params)")
    public ResponseEntity<CalculationResponse> subtractGet(
            @RequestParam @NotNull Double a,
            @RequestParam @NotNull Double b) {
        return ResponseEntity.ok(calculatorService.subtract(a, b));
    }

    @GetMapping("/multiply")
    @Operation(summary = "Multiply two numbers (query params)")
    public ResponseEntity<CalculationResponse> multiplyGet(
            @RequestParam @NotNull Double a,
            @RequestParam @NotNull Double b) {
        return ResponseEntity.ok(calculatorService.multiply(a, b));
    }

    @GetMapping("/divide")
    @Operation(summary = "Divide a by b (query params)")
    public ResponseEntity<CalculationResponse> divideGet(
            @RequestParam @NotNull Double a,
            @RequestParam @NotNull Double b) {
        return ResponseEntity.ok(calculatorService.divide(a, b));
    }

    @GetMapping("/modulo")
    @Operation(summary = "Compute a modulo b (query params)")
    public ResponseEntity<CalculationResponse> moduloGet(
            @RequestParam @NotNull Double a,
            @RequestParam @NotNull Double b) {
        return ResponseEntity.ok(calculatorService.modulo(a, b));
    }

    @GetMapping("/power")
    @Operation(summary = "Raise a to the power b (query params)")
    public ResponseEntity<CalculationResponse> powerGet(
            @RequestParam @NotNull Double a,
            @RequestParam @NotNull Double b) {
        return ResponseEntity.ok(calculatorService.power(a, b));
    }

    @GetMapping("/sqrt")
    @Operation(summary = "Square root of a number")
    public ResponseEntity<CalculationResponse> sqrtGet(
            @Parameter(description = "Number to compute the square root of")
            @RequestParam @NotNull Double a) {
        return ResponseEntity.ok(calculatorService.squareRoot(a));
    }
}
