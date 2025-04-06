import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.html.*
import kotlinx.html.*
import java.io.File

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        routing {
            get("/") {
                call.respondText("Server is running!")
            }

            get("/text") {
                val textFile = File("resources/sample.txt")
                if (textFile.exists()) {
                    call.respondText(textFile.readText())
                } else {
                    call.respondText("File not found", status = HttpStatusCode.NotFound)
                }
            }

            get("/website") {
                call.respondHtml {
                    head {
                        title { +"Kotlin Server" }
                    }
                    body {
                        h1 { +"Welcome to Kotlin Web Server" }
                        p { +"This is a simple website served from a Kotlin application." }
                        p {
                            +"You can get the text file content from "
                            a("/text") { +"/text endpoint" }
                        }
                    }
                }
            }
        }
    }.start(wait = true)
}