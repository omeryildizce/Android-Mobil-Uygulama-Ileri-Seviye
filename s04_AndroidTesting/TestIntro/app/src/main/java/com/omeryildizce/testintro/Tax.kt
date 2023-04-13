package com.omeryildizce.testintro

class Tax {
    // TDD - Test Driven Development
    // Development - Test - Development - Test

    fun calculateTax(grossIncome: Double, taxRate: Double): Double {
        return grossIncome * taxRate
    }

    fun calculateIncome(grossIncome: Double, taxRate: Double): Double {
        return grossIncome - (grossIncome * taxRate)
    }
}