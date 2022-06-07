import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class MyClient {
    lateinit var client: Socket
    lateinit var output: PrintWriter
    lateinit var input: BufferedReader

    fun getClientNumber(): Int {
        val tmp = input.readLine()
        println(tmp)
        return tmp.toInt()
    }

    fun startConnection(host: String, port: Int) {
        client = Socket(host, port)
        output = PrintWriter(client.getOutputStream(), true)
        input = BufferedReader(InputStreamReader(client.getInputStream()))
        println("Client connected ${client.inetAddress.hostAddress}")
    }

    fun sendMessage(message: String) : String {
        output.println(message)
        return input.readLine()
    }

    fun stopConnection() {
        client.close()
        output.close()
        input.close()
        println("${client.inetAddress.hostAddress} closed the connection")
    }


}