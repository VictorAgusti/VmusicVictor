package es.vag.vmusic.search_function

import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyAPI {
    @GET("character/")
    suspend fun getCharacters(@Query("status") status: String): ApiResponse
}
