package com.example.demo

import groovy.json.JsonOutput
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import org.springframework.test.context.junit4.SpringRunner

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.MatcherAssert.assertThat

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = [MyLoggerServiceApplication])
class MyLoggerServiceApplicationTest {

    @Autowired
    private Processor processor

    @Autowired
    private MessageCollector messageCollector

    @Test
    @SuppressWarnings("unchecked")
    void testWiring() {
        Person person = new Person(name: "Arun Menon")
        Message<Person> message = new GenericMessage<>(person)
        processor.input().send(message)
        Message<Person> received = (Message<Person>) messageCollector.forChannel(processor.output()).poll()
        assertThat(received.getPayload(), equalTo(JsonOutput.toJson(person)))
    }
}
