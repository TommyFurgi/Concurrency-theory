import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking {
    val N = 5
    val channels = List(N + 1) { Channel<String>() }

    val producerJob = launch { producer(channels[0]) }
    val processorJobs = (0 until N).map { i ->
        launch { processor(i, channels[i], channels[i + 1]) }
    }
    val consumerJob = launch { consumer(channels[N]) }

    delay(5000)

    producerJob.cancel()
    consumerJob.cancel()
    processorJobs.forEach { it.cancel() }

    channels.forEach { it.close() }
}

suspend fun producer(output: Channel<String>) {
    var counter = 0
    while (true) {
        delay(1000)
        val product = "Product-${counter++}"
        println("Producer: produced $product")
        output.send(product)
    }
}

suspend fun processor(id: Int, input: Channel<String>, output: Channel<String>) {
    for (product in input) {
        println("Processor $id: processing $product")
        delay(300)
        output.send("$product -> processed by $id")
    }
}

suspend fun consumer(input: Channel<String>) {
    for (product in input) {
        println("Consumer: consumed $product")
        delay(400)
    }
}
