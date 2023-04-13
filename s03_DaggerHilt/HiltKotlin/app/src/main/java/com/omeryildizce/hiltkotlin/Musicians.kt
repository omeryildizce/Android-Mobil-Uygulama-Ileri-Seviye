package com.omeryildizce.hiltkotlin

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
// Constructor injection
class Musicians @Inject constructor(instrument: Instrument,band: Band){
     fun sing(){
         println("working ........................")
     }
}