package com.battousai.gamelist.network

import com.battousai.gamelist.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.*
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkHelper {

    companion object {

        val service: ApiService = getRetrofitInstance().create(ApiService::class.java)


        private fun getRetrofitInstance(): Retrofit {
            val gson = GsonBuilder().setLenient().create()
            val gsonConverterFactory = GsonConverterFactory.create(gson)

            val httpClientBuilder = OkHttpClient.Builder()
            val httpClient = httpClientBuilder.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()
                    val originalHttpUrl: HttpUrl = original.url
                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("key", Constants.API_KEY)
                        .build()

                    val requestBuilder: Request.Builder = original.newBuilder()
                        .url(url)
                    val request: Request = requestBuilder.build()
                    return chain.proceed(request)
                }
            }).build()


            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient).build()
        }
    }


}