package training.ram.kotlinCleanArchitecture.data.remote

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import training.ram.kotlinCleanArchitecture.data.entities.Customer

interface ServiceApi {
    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/rmoustafa/Clean-Architecture-with-Architecture-components/master/"
    }

    @GET("customers.json")
    fun getCustomerList(): Deferred<Response<List<Customer>>>



}