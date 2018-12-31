package training.ram.kotlinCleanArchitecture.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import training.ram.kotlinCleanArchitecture.domain.GetCustomersUseCase
import training.ram.kotlinCleanArchitecture.ui.customers.CustomersViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val getCustomersUseCase: GetCustomersUseCase): ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass != CustomersViewModel::class.java){
            throw IllegalArgumentException("unknown ViewModel")
        }
        return CustomersViewModel(getCustomersUseCase) as T
    }

}