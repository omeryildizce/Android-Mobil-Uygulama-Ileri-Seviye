
package com.omeryildizce.hiltkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // field injection
    @Inject
    lateinit var lars: Musicians

    @Inject
    lateinit var myClass : ClassExample
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lars.sing()
        println( myClass.myFunction())
        println(myClass.seconfFunction())
    }
}

