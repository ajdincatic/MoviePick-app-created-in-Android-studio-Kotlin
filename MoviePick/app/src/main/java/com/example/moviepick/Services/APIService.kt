package com.example.moviepick.Services

import android.util.Base64
import com.example.moviepick.Model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import javax.net.ssl.*

object APIService {
    private const val URL = " https://moviepick.p1859.app.fit.ba/api/"
    var username: String = ""
    var password: String = ""
    var loggedUser: User? = null
    var basicAuthHeader: String = ""

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttp = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor{ chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", basicAuthHeader)
                        .build()
                    chain.proceed(newRequest)
                }

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())

    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T{
        return retrofit.create(serviceType)
    }

    fun setHeader(){
        basicAuthHeader = "Basic " + Base64.encodeToString(("$username:$password").toByteArray(),Base64.NO_WRAP)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return try {
            val trustAllCerts: Array<TrustManager> = arrayOf(MyManager())
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory())
                .addInterceptor(logging)
                .hostnameVerifier { hostname: String?, session: SSLSession? -> true }
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

class MyManager : X509TrustManager {

    override fun checkServerTrusted(
        p0: Array<out java.security.cert.X509Certificate>?,
        p1: String?
    ) {
        //allow all
    }

    override fun checkClientTrusted(
        p0: Array<out java.security.cert.X509Certificate>?,
        p1: String?
    ) {
        //allow all
    }

    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
        return arrayOf()
    }
}