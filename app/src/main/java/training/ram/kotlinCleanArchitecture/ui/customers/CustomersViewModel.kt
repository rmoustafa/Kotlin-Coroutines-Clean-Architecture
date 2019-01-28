package training.ram.kotlinCleanArchitecture.ui.customers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import training.ram.kotlinCleanArchitecture.data.entities.Customer
import training.ram.kotlinCleanArchitecture.data.remote.ApiResponse
import training.ram.kotlinCleanArchitecture.domain.GetCustomersUseCase
import javax.inject.Inject

/**
 * expose data to view
 * trigger actions on use cases based on user actions
 * launch and cancel coroutines
 */
class CustomersViewModel @Inject constructor(private val getCustomersUseCase: GetCustomersUseCase): ViewModel(){
    private val _customers = MutableLiveData<List<Customer>>()
    val customers: LiveData<List<Customer>>
        get() = _customers
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    private var job: Job? = null

    init {
        getCustomerList()
    }

    fun getCustomerList(){
        _isLoading.value = true
        job = CoroutineScope(Dispatchers.Default).launch{
            val result = getCustomersUseCase(true)
            _isLoading.postValue(false)
            when(result){
                is ApiResponse.Success -> _customers.postValue(result.items)
                is ApiResponse.Error -> _error.postValue(result.errorMessage)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}