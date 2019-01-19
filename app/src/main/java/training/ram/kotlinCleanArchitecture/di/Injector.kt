package training.ram.kotlinCleanArchitecture.di

import training.ram.kotlinCleanArchitecture.base.SampleApplication

class Injector {
    companion object {
        fun get() = SampleApplication.applicationComponent
    }
}