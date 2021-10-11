package com.example.buttercms.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.buttercms.ApiCallService

class Demo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client = ApiCallService("3606556ecbd4134ea24b8936a829ab9edaddb583")

        Thread {
            try {
                // Post
                val l = client.data.getPost("example-2").execute()
                println(l)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.start()
    }
}
