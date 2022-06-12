package model

import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*

class Client(address: String, port: Int, val id: Int) {
     private val connection: Socket = Socket(address, port)

    init {
        println("Connected client $id to server at $address on port $port")
    }

    val reader: Scanner = Scanner(connection.getInputStream())
    val writer: OutputStream = connection.getOutputStream()

     fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

}