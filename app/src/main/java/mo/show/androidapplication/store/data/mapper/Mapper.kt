package mo.show.androidapplication.store.data.mapper

import android.net.http.HttpException
import mo.show.androidapplication.store.domain.model.ApiError
import mo.show.androidapplication.store.domain.model.NetworkError
import java.io.IOException

fun Throwable.toNetworkError(): NetworkError {
    val error = when(this){
        is HttpException -> ApiError.UnknownResponse
        is IOException -> ApiError.NetworkError
        else -> ApiError.UnknownError
    }
    return NetworkError(
        error = error,
        t=this
    )
}