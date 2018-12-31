package training.ram.kotlinCleanArchitecture.data.remote

sealed class ApiResponse <out T: Any>{
    data class Success<T: Any>( val items: T?): ApiResponse<T>()
    data class Error(val erroCOde: Int, val errorMessage: String): ApiResponse<Nothing>()
    data class Exception(val exception: kotlin.Exception): ApiResponse<Nothing>()
}