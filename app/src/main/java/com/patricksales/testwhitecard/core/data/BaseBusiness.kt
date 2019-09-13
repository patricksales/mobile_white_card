package com.patricksales.testwhitecard.core.data

import com.patricksales.testwhitecard.core.data.api.ResponseApi
import com.patricksales.testwhitecard.core.util.APIError.ERROR_DEFAULT
import com.patricksales.testwhitecard.core.util.ErrorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Response

import java.lang.Exception
import kotlin.coroutines.CoroutineContext

open class BaseBusiness(
    private val repository: Repository? = null) {

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    val scope = CoroutineScope(coroutineContext)

    suspend fun <T: Any> callApi(call: suspend ()-> Response<T>) : ResponseApi {
        try {
            val response = call.invoke()

            return if(response.isSuccessful) {
                ResponseApi.success(response.body())
            } else {
                val error = ErrorUtils.parseError(response)

                error?.message?.let {  message ->
                    ResponseApi.error(message)
                } ?: run {
                    ResponseApi.error(ERROR_DEFAULT)
                }
            }
        } catch (error : Exception) {
            return ResponseApi.error(ERROR_DEFAULT)
        }
    }
}