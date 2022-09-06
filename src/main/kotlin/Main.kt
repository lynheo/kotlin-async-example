import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

var format = SimpleDateFormat("yyyy-MM-ddTHH:mm:ss.SSS")

fun dbInsertFunc() {
    println("[${format.format(Calendar.getInstance().time)}] Insert Start")
    Thread.sleep(10000) //Long Time Blocking IO
    println("[${format.format(Calendar.getInstance().time)}] Insert End")
}

suspend fun main() = coroutineScope {
    val insertContext = newFixedThreadPoolContext(1000, "insertThreadPool")

    for (i in 0 until 10000000) {
        launch(insertContext) {
            dbInsertFunc()
        }
    }
}