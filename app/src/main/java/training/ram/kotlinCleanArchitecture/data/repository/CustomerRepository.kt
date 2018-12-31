package training.ram.kotlinCleanArchitecture.data.repository

import training.ram.kotlinCleanArchitecture.data.entities.Customer
import training.ram.kotlinCleanArchitecture.data.remote.ApiResponse

interface CustomerRepository {
     suspend fun getCustomers(refresh: Boolean): ApiResponse<List<Customer>>
}