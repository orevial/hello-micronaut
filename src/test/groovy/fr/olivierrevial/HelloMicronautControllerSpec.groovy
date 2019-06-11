package fr.olivierrevial


import io.micronaut.context.annotation.Property
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class HelloMicronautControllerSpec extends Specification {

    @Inject
    @Client('/')
    RxHttpClient client

    @Property(name = "app.env-user", value = "Test User")
    void "test simple hello with overriden property"() {
        expect:
        client.toBlocking()
                .retrieve(HttpRequest.GET('/hello/simple')) == "Hello Test User !"
    }

    void "test simple hello with given param"() {
        expect:
        client.toBlocking()
                .retrieve(HttpRequest.GET('/hello/simple?myUser=Olivier')) == "Hello Olivier !"
    }
}
