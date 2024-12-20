import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select
import kotlin.random.Random

fun main() = runBlocking {
    val channels = List(10) { Pair(Channel<Int>(), Channel<Int>()) }

    channels.forEachIndexed { index, (fromProducer, toConsumer) ->
        launch { broker(index, fromProducer, toConsumer) }
    }

    val producerJob = launch { producer(channels.map { it.first }) }
    val consumerJob = launch { consumer(channels.map { it.second }) }

    delay(5000)

    producerJob.cancel()
    consumerJob.cancel()

    channels.forEach { (fromProducer, toConsumer) ->
        fromProducer.close()
        toConsumer.close()
    }
}

suspend fun producer(brokers: List<Channel<Int>>) {
    repeat(20) { iteration ->
        delay(200)
        val value = Random.nextInt(100)
        select {
            brokers.forEachIndexed { index, channel ->
                channel.onSend(value) {
                    println("Producer sent value $value to broker $index")
                }
            }
        }
    }
}

suspend fun consumer(brokers: List<Channel<Int>>) {
    while (true) {
        select {
            brokers.forEachIndexed { index, channel ->
                channel.onReceive { value ->
                    println("Consumer received value $value from broker $index")
                }
            }
        }
        delay(900)
    }
}

suspend fun broker(index: Int, fromProducer: Channel<Int>, toConsumer: Channel<Int>) {
    for (value in fromProducer) {
        println("Broker $index forwarding value $value")
        toConsumer.send(value)
    }
}