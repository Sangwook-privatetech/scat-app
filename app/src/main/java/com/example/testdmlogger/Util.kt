package com.example.testdmlogger

import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

//fun String.runCommand(workingDir: File): String? {
fun String.runCommand(workingDir: File): String {
    try {
        val parts = this.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
            .directory(workingDir)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor(60, TimeUnit.MINUTES)
        return proc.inputStream.bufferedReader().readText()
    } catch(e: IOException) {
        e.printStackTrace()
//        return null
        return ""
    }

}

interface retrofit_interface {
    @Multipart
    @POST("fileUpload/")
    fun post_Pcapfile_Request(
        @Part pcapFile : MultipartBody.Part
    ): Call<String>
 }
