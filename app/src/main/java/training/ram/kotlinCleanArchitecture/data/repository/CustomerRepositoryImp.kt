package training.ram.kotlinCleanArchitecture.data.repository

import training.ram.kotlinCleanArchitecture.data.entities.Customer
import training.ram.kotlinCleanArchitecture.data.local.CustomerDAO
import training.ram.kotlinCleanArchitecture.data.remote.ApiResponse
import training.ram.kotlinCleanArchitecture.data.remote.ServiceApi
import training.ram.kotlinCleanArchitecture.utils.safeApiCall
import javax.inject.Inject

class CustomerRepositoryImp @Inject constructor(private val apiServiceApi: ServiceApi, private val customerDao: CustomerDAO) :CustomerRepository{

    override suspend fun getCustomers(refresh: Boolean): ApiResponse<List<Customer>> {
        return if(refresh){
            safeApiCall(
                call = { getCustomerList(refresh)},
                errorMessage = "Exception occurred!"
            )
        } else{
            return ApiResponse.Success(customerDao.getCustomers())
        }

    }

    private suspend fun getCustomerList(refresh: Boolean): ApiResponse<List<Customer>> {
            val result = apiServiceApi.getCustomerList().await()
            if (result.isSuccessful) {
                cacheCustomers(result.body())
                return ApiResponse.Success(result.body())
            }
            return ApiResponse.Error(result.code(), result.message())
    }

    private fun cacheCustomers(customers: List<Customer>?){
        if(customers != null) {
            customerDao.deleteAllCustomers()
            customerDao.saveCustomers()
        }
    }

}