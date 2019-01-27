package training.ram.kotlinCleanArchitecture.data.repository

import androidx.annotation.VisibleForTesting
import training.ram.kotlinCleanArchitecture.data.entities.Customer
import training.ram.kotlinCleanArchitecture.data.local.CustomerDAO
import training.ram.kotlinCleanArchitecture.data.remote.ApiResponse
import training.ram.kotlinCleanArchitecture.data.remote.ServiceApi
import training.ram.kotlinCleanArchitecture.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerRepositoryImp @Inject constructor(
    private val apiServiceApi: ServiceApi,
    private val customerDao: CustomerDAO
): CustomerRepository{

    override suspend fun getCustomers(refresh: Boolean): ApiResponse<List<Customer>> {
        return if(refresh){
            safeApiCall(
                call = { getCustomerList()},
                errorMessage = "Exception occurred!"
            )
        } else{
            return ApiResponse.Success(customerDao.getCustomers())
        }

    }

    private suspend fun getCustomerList(): ApiResponse<List<Customer>> {
            val result = apiServiceApi.getCustomerList().await()
            if (result.isSuccessful) {
                cacheCustomers(result.body())
                return ApiResponse.Success(result.body())
            }
            return ApiResponse.Error(result.code(), result.message())
    }

    @VisibleForTesting
    internal fun cacheCustomers(customers: List<Customer>?){
        if(customers != null) {
            customerDao.deleteAllCustomers()
            customerDao.saveCustomers(customers)
        }
    }

}