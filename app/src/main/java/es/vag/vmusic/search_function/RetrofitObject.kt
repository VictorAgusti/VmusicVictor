package es.vag.vmusic.search_function

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitObject {

    companion object{
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit{
            synchronized(this) {
                if (instance == null) {
                    instance = Retrofit.Builder()
                        .baseUrl("https://rickandmortyapi.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return instance!!
            }
        }
    }
}