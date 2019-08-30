package com.example.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.messaging.handler.annotation.SendTo

@SpringBootApplication
@EnableBinding(Processor)
class MyLoggerServiceApplication {


    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    Person handle(Person person) {
        println "Received: $person"
        person
    }


    static void main(String[] args) {
        SpringApplication.run(MyLoggerServiceApplication.class, args)
    }
}
