package com.example.plagiarism.message

import com.example.plagiarism.handler.MessageHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Publisher (
    private val kafkaTemplate: KafkaTemplate<String, String>
    ){
    suspend fun report(message: String){
        kafkaTemplate.send("report",message)
    }
}