package training.ram.kotlinCleanArchitecture.domain

import training.ram.kotlinCleanArchitecture.data.entities.Customer
import training.ram.kotlinCleanArchitecture.data.remote.ApiResponse
import training.ram.kotlinCleanArchitecture.data.repository.CustomerRepositoryImp
import javax.inject.Inject

class GetCustomersUseCase @Inject constructor(private val customerRepositoryImp: CustomerRepositoryImp) {

      suspend operator fun invoke(refresh: Boolean): ApiResponse<List<Customer>> {
        return customerRepositoryImp.getCustomers(refresh)
    }
}