package training.ram.kotlinCleanArchitecture.data.remote

import training.ram.kotlinCleanArchitecture.data.entities.Customer

class CustomerRemoteDataSource (val serviceApi: ServiceApi){

   /* suspend fun getCustomers(): ApiResponse<List<Customer>>{
        val request = serviceApi.getCustomerList()
        val response = request.await()

    }
*/
}