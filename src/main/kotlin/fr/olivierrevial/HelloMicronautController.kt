package fr.olivierrevial

import io.micronaut.context.annotation.Value
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

@Controller("/hello")
class HelloMicronautController(@Value("\${app.env-user}") val user: String) {

    @Get("/simple")
    fun helloSimple(myUser: String?): String {
        return "Hello ${myUser ?: user} !"
    }

    @Get("/reactive")
    fun helloReactive(): Flowable<String> {
        return Flowable.intervalRange(0, 20, 1, 1, TimeUnit.SECONDS)
                .map { i -> """Hello Micronaut n°$i""" }
    }
}