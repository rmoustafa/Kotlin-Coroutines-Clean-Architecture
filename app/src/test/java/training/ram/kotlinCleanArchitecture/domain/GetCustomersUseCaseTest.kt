package training.ram.kotlinCleanArchitecture.domain

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.*
import training.ram.kotlinCleanArchitecture.data.entities.Customer
import training.ram.kotlinCleanArchitecture.data.remote.ApiResponse
import training.ram.kotlinCleanArchitecture.data.repository.CustomerRepositoryImp

class GetCustomersUseCaseTest {
    private lateinit var useCase: GetCustomersUseCase
    private val mockRepo = mock(CustomerRepositoryImp::class.java)
    private val customerList = listOf(Customer(1,"Mick","roberto"))

    @Before
    fun setUp() {
        useCase = GetCustomersUseCase(mockRepo)
    }

    @Test
    fun `test Get Customers returns success`() {
        runBlocking {
            `when`(mockRepo.getCustomers(true)).thenReturn(ApiResponse.Success(customerList))
            val result = useCase.invoke(true)
            verify(mockRepo).getCustomers(true)
            Assert.assertEquals(result, ApiResponse.Success(customerList))


        }
    }

    @Test
    fun `test Get Customers returns fail`() {
        runBlocking {
            `when`(mockRepo.getCustomers(true)).thenReturn(ApiResponse.Error(404,"error occured"))
            val result = useCase.invoke(true)
            verify(mockRepo).getCustomers(true)
            Assert.assertEquals(result, ApiResponse.Error(404, "error occured"))


        }
    }
}