package com.example.plagiarism.handler

import com.example.plagiarism.message.Publisher
import org.springframework.stereotype.Component

@Component
class MessageHandler(
    private val publisher : Publisher
) {
    suspend fun report(s: String){
        publisher.report(s)
    }
}