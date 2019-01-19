package training.ram.kotlinCleanArchitecture.di.components

import dagger.Component
import training.ram.kotlinCleanArchitecture.di.ViewModelFactory
import training.ram.kotlinCleanArchitecture.di.modules.ApplicationModule
import training.ram.kotlinCleanArchitecture.di.modules.NetworkModule
import training.ram.kotlinCleanArchitecture.di.modules.RoomDBModule
import training.ram.kotlinCleanArchitecture.ui.customers.CustomersViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RoomDBModule::class, ApplicationModule::class])

interface ApplicationComponent {

    fun viewModelFactory(): ViewModelFactory<CustomersViewModel>
}