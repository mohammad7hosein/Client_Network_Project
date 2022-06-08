import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*

class Client(address: String, port: Int, val id: Int) {
     private val connection: Socket = Socket(address, port)
     private var connected: Boolean = true

    init {
        println("Connected client $id to server at $address on port $port")
    }

    val reader: Scanner = Scanner(connection.getInputStream())
    val writer: OutputStream = connection.getOutputStream()

    fun run() {
//        thread { read() }
        while (connected) {
            val input = readLine() ?: ""
            if ("exit" in input) {
                connected = false
                reader.close()
                connection.close()
            } else {
                write(input)
            }
        }

    }

     fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

    private fun read() {
        while (connected)
            println(reader.nextLine())
    }
}