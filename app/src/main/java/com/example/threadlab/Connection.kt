package com.example.threadlab

import android.os.Handler
import android.util.Log
import java.io.InputStream
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class Connection(mHand: Handler): Runnable {

    private val myHandler = mHand

    override fun run() {
        try{
            val myUrl = URL("https://www.w3.org/TR/PNG/iso_8859-1.txt")
            val myConn = myUrl.openConnection() as HttpURLConnection
            myConn.requestMethod = "GET"

            val istream: InputStream = myConn.inputStream
            val text = istream.bufferedReader().use{it.readText()}
            val result = StringBuilder()
            result.append(text)
            val str = result.toString()
            val msg = myHandler.obtainMessage()
            msg.what = 0
            msg.obj = str
            myHandler.sendMessage(msg)

        } catch (e : Exception){
            Log.d("Error", e.toString())
        }
    }
}