package com.omeryildizce.hiltkotlin

import javax.inject.Inject

class SecondInterfaceInplementor
@Inject constructor() : MyInterface {
    override fun myPrintFunction(): String {

        return "My Second Interface Implementor"
    }

}