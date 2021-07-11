package com.example.plagiarism.message

import com.example.plagiarism.handler.MessageHandler
import kotlinx.coroutines.runBlocking
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Subscriber(private val messageHandler: MessageHandler) {
    @KafkaListener(
        topics = ["plagiarism"],
        groupId = "plagiarism-group"
    )
    fun subscriberMessage(message: String) {
        val submissions = message.split(";")
        var plagio : MutableList<Boolean> = arrayListOf()
        val template = submissions[0]
        println(submissions.size-1)
        for (i in 0..submissions.size-1){
            plagio.add(i,false)
            println(i)
        }
        for (i in 1..submissions.size-1){
            for(j in (i+1)..submissions.size-1){
                var equals = 0
                if (!template.equals(submissions[i]))
                    for(k in 0..submissions[i].length-1){
                        if (k<submissions[i].length && k<submissions[j].length)
                        if (submissions[i][k] == submissions[j][k]){
                            equals += 1
                        }
                    }
                equals = (100*equals)/template.length
                if(equals>=75){
                    plagio[j]=true
                    plagio[i]=true
                }

            }

        }
        runBlocking {
            messageHandler.report(plagio.toString())
        }
    }
}