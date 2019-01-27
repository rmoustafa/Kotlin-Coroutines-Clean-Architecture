package training.ram.kotlinCleanArchitecture.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response.success
import training.ram.kotlinCleanArchitecture.data.entities.Customer
import training.ram.kotlinCleanArchitecture.data.local.CustomerDAO_Impl
import training.ram.kotlinCleanArchitecture.data.remote.ServiceApi

class CustomerRepositoryImpTest {

    private lateinit var repo: CustomerRepositoryImp
    private val mockServiceApi = mock(ServiceApi::class.java)
    private val mockDao = mock(CustomerDAO_Impl::class.java)
    val customers = listOf(Customer(1,"mark","Roberto"))

    @Before
    fun setUp() {
        repo = CustomerRepositoryImp(mockServiceApi, mockDao)
    }

    @Test
    fun `get Customers from remote api`() {
        runBlocking {
            val response = success(customers)
            val deferred = async { response  }

            `when`(mockServiceApi.getCustomerList()).thenReturn(deferred)
           val result = repo.getCustomers(true)
            verify(mockServiceApi).getCustomerList()
            //Assert.assertEquals(ApiResponse.Success(customers), ApiResponse.Success(result))
        }
        verify(mockDao).deleteAllCustomers()
        verify(mockDao).saveCustomers(customers)
    }

    @Test
    fun `get Customers from remote local`() {
        runBlocking {

            `when`(mockDao.getCustomers()).thenReturn(customers)
            repo.getCustomers(false)
            verify(mockDao).getCustomers()

        }
    }
}