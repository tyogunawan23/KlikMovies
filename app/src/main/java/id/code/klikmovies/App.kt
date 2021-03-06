package id.code.klikmovies

import android.app.Activity
import android.app.Application
import android.content.Intent
import id.code.klikmovies.api.ApiServices
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.HttpUrl
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class App : Application (){

    companion object {
        val baseUrl : String =  "https://api.themoviedb.org/3/"
        val apiKey : String = "cd14689fcbad1f976e0851a19ba2216f"
        val posterPath : String = "https://image.tmdb.org/t/p/original"
        val BROADCAST_FAVORITE_CHANGED = "BROADCAST_FAVORITE_CHANGED"
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()
                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val services: ApiServices = retrofit.create(ApiServices::class.java)


    public fun sendBroadcastFavoriteChanged (act: Activity){
        try {
            val intent = Intent()
            intent.action = App.Companion.BROADCAST_FAVORITE_CHANGED
            act.sendBroadcast(intent)
        }catch (e:Exception){
            e.printStackTrace()
        }

    }


}