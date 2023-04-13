package com.omeryildizce.hiltkotlin

import com.google.gson.Gson
import javax.inject.Inject

class ClassExample @Inject constructor(
    @FirstImplementor private val myInterfaceImplementor: MyInterface,
    private val gson: Gson,
    @SecondImplementor private val mySecondInterfaceInplementor: MyInterface
    ) {
    fun myFunction(): String {
        return "Working ${myInterfaceImplementor.myPrintFunction()}"
    }
    fun seconfFunction() : String{
        return "Working ${mySecondInterfaceInplementor.myPrintFunction()}"
    }

}