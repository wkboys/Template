package com.template.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.template.wk.R
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_coroutines)
//        log("start")
//        GlobalScope.launch (context = Dispatchers.IO) {
//            //延时5秒
//            delay(2000)
//            log("launch")
//        }
//        //主动休眠两秒，防止JVM过快退出
//        Thread.sleep(2000)
//        log("end")
   /*     suspend 修饰方法 用于暂停执行当前协程，并保存所有局部变量
        resume 修饰方法 用于让已暂停的协程从暂停处继续执行*/

//        log("start")
//        GlobalScope.launch {
//            launch {
//                delay(400)
//                log("launch A")
//            }
//            launch {
//                delay(300)
//                log("launch B")
//            }
//            log("GlobalScope")
//        }
//        log("end")
//        Thread.sleep(500)
       /* GlobalScope。即全局协程作用域，在这个范围内启动的协程可以一直运行直到应用停止运行。
        GlobalScope 本身不会阻塞当前线程，且启动的协程相当于守护线程，不会阻止 JVM 结束运行*/

//        log("start")
//        runBlocking {
//            launch {
//                repeat(3) {
//                    delay(100)
//                    log("launchA - $it")
//                }
//            }
//            launch {
//                repeat(3) {
//                    delay(100)
//                    log("launchB - $it")
//                }
//            }
//            GlobalScope.launch {
//                repeat(3) {
//                    delay(120)
//                    log("GlobalScope - $it")
//                }
//            }
//        }
//        log("end")

//        log("start")
//        GlobalScope.launch(Dispatchers.IO) {
//            delay(600)
//            log("GlobalScope")
//        }
//        runBlocking {
//            delay(500)
//            log("runBlocking")
//        }
//        //主动休眠两百毫秒，使得和 runBlocking 加起来的延迟时间少于六百毫秒
//        Thread.sleep(200)
//        log("after sleep")

//        runBlocking{
//            launch {
//                delay(100)
//                log("Task from runBlocking")
//            }
//            coroutineScope {
//                launch {
//                    delay(500)
//                    log("Task from nested launch")
//                }
//                delay(100)
//                log("Task from coroutine scope")
//            }
//            log("Coroutine scope is over")
//        }

        runBlocking {
            launch {
                delay(100)
                log("Task from runBlocking")
            }
            supervisorScope {
                launch {
                    delay(500)
                    log("Task throw Exception")
                    throw Exception("failed")
                }
                launch {
                    delay(600)
                    log("Task from nested launch")
                }
            }
            log("Coroutine scope is over")
        }


    }

    suspend fun fetchDocs() {                             // Dispatchers.Main
        val result = get("https://developer.android.com") // Dispatchers.IO for `get`
//        show(result)                                      // Dispatchers.Main
    }

    suspend fun get(url: String) = withContext(Dispatchers.IO) { /* ... */ }


    private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")
}