package training.ram.kotlinCleanArchitecture.di.modules

import dagger.Module
import dagger.Provides
import training.ram.kotlinCleanArchitecture.base.SampleApplication
import training.ram.kotlinCleanArchitecture.di.scopes.ApplicationScope

@Module
class ApplicationModule(private val application: SampleApplication) {

    @ApplicationScope
    @Provides
    fun provideContext() : SampleApplication{
        return application
    }

}