package training.ram.kotlinCleanArchitecture.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import training.ram.kotlinCleanArchitecture.base.SampleApplication
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: SampleApplication) {


    @Provides
    @Singleton
    fun provideContext() : Context{
        return application.applicationContext
    }

}