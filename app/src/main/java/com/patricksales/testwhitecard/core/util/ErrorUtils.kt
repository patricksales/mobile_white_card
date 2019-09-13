package com.patricksales.testwhitecard.core.util

import com.patricksales.testwhitecard.core.data.api.WhiteCardService
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

object ErrorUtils {

    object ApiError {
        var statusCode: Int? = null
        var message: String? = null
    }

    fun parseError(response: Response<*>): ApiError? {
        val converter: Converter<ResponseBody, ApiError> = WhiteCardService.getWhiteCardClient()
            .responseBodyConverter(ApiError::class.java, arrayOfNulls<Annotation>(0))

        var error: ApiError? = null

        try {
            response.errorBody()?.let { errorBody ->
                error = converter.convert(errorBody)
            }
        } catch (e: IOException) {
            return ApiError
        }

        return error
    }
}