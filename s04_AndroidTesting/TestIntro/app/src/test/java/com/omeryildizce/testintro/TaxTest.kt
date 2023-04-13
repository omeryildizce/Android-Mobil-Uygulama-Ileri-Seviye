package com.omeryildizce.testintro

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.internal.artificialFrame
import org.junit.After
import org.junit.Before
import org.junit.Test


class TaxTest {
    private lateinit var tax: Tax

    @Before
    fun setuo() {
        tax = Tax()

    }

    @After
    fun teardown(){

    }

    @Test
    fun calculateTax() {
        val netTax = tax.calculateTax(100.0, 0.1)
        assertThat(netTax).isEqualTo(10.0)

    }

    @Test
    fun calculateIncome() {
        val income = tax.calculateIncome(100.0, 0.1)
        assertThat(income).isEqualTo(90)
    }
}