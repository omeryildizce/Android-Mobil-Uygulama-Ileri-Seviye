package com.omeryildizce.hiltkotlin

import androidx.transition.Visibility.Mode
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Qualifier

interface MyInterface {
    fun myPrintFunction() : String
}


/*
@InstallIn(ActivityComponent::class)
@Module
abstract class MyModule{
    @ActivityScoped
    @Binds
    abstract fun bindingFunction(myImplementor: InterfaceImplementor) : MyInterface
}

 */


@InstallIn(ActivityComponent::class)
@Module
class MyModule{
    @FirstImplementor
    @ActivityScoped
    @Provides
    fun providerFunction() : MyInterface{
        return InterfaceImplementor()
    }
    @SecondImplementor
    @ActivityScoped
    @Provides
    fun secondProviderFunction() : MyInterface{
        return SecondInterfaceInplementor()
    }

    @ActivityScoped
    @Provides
    fun gsonProvider() : Gson{
        return Gson()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirstImplementor


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SecondImplementor