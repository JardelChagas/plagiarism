package com.example.plagiarism.message

import kotlinx.coroutines.runBlocking
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.*

@Component
class Subscriber {
    @KafkaListener(
        topics = ["plagiarism"],
        groupId = "plagiarism-group"
    )
    fun subscriberMessage(message: String) {
        val ff = message.split(";")
        print(message)
    }

}