package com.org.asynanwait

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

/**
 * Async and Await example.
 * Coroutine launcher pattern and Async and Await work almost similarly. The only difference is with Async and wait you get the result outside the corountine
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            fakeAPICall()
        }

    }

    private fun fakeAPICall() {

        CoroutineScope(IO).launch {

            val result1: Deferred<String> = async {
                fakeAPI1()
            }

            println("Debug result1= ${result1.await()}")

            launch {
                val result2 = fakeAPI2()
                println("Debug result1 = $result2")
            }


        }

    }

    public suspend fun fakeAPI1(): String {
        delay(1000)
        return "RESULT_1"
    }

    public suspend fun fakeAPI2(): String {
        delay(1000)
        return "RESULT_2"
    }
}
