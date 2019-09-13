package com.example.testwhitecard.core.data.api

data class ResponseApi(val status: StatusResponse, val message: String?, val data: Any?) {

    enum class StatusResponse {
        SUCCESS,
        ERROR
    }

    companion object {
        fun error(msg: String): ResponseApi {
            return ResponseApi(StatusResponse.ERROR, msg, null)
        }

        fun success(data: Any?): ResponseApi {
            return ResponseApi(StatusResponse.SUCCESS, null, data)
        }
    }
}