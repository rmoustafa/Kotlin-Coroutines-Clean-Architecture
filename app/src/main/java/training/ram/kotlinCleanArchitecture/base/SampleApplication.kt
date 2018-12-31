package training.ram.kotlinCleanArchitecture.base

import android.app.Application
import timber.log.Timber
import training.ram.kotlinCleanArchitecture.di.components.ApplicationComponent
import training.ram.kotlinCleanArchitecture.di.components.DaggerApplicationComponent
import training.ram.kotlinCleanArchitecture.di.modules.ApplicationModule
import training.ram.kotlin_cleanarchitecture.BuildConfig

class SampleApplication : Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }


    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        applicationComponent =
                DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .build()
    }
}