package training.ram.kotlinCleanArchitecture.utils

import training.ram.kotlinCleanArchitecture.data.remote.ApiResponse
import java.io.IOException


suspend fun <T: Any> safeApiCall(
    call: suspend () -> ApiResponse<T>,
    errorMessage: String
): ApiResponse<T>{
    return try {
        call()
    }catch (e: Exception){
        ApiResponse.Exception(IOException(errorMessage, e))
    }
}
