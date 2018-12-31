package training.ram.kotlinCleanArchitecture.di.components

import dagger.Component
import training.ram.kotlinCleanArchitecture.base.SampleApplication
import training.ram.kotlinCleanArchitecture.di.modules.ApplicationModule
import training.ram.kotlinCleanArchitecture.di.modules.NetworkModule
import training.ram.kotlinCleanArchitecture.di.modules.RoomDBModule
import training.ram.kotlinCleanArchitecture.di.scopes.ApplicationScope
import training.ram.kotlinCleanArchitecture.ui.customers.CustomersActivity

@ApplicationScope
@Component(modules = [NetworkModule::class, RoomDBModule::class, ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: SampleApplication)
    fun inject(customersActivity: CustomersActivity)
}