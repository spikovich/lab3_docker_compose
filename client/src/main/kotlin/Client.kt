import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val client = HttpClient(CIO)

    try {
        println("Requesting text from server...")
        val response: HttpResponse = client.get("http://server:8080/text")
        val text = response.bodyAsText()

        println("Received text from server:")
        println("=".repeat(50))
        println(text)
        println("=".repeat(50))
    } catch (e: Exception) {
        println("Error: ${e.message}")
    } finally {
        client.close()
    }
}