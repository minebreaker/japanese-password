package rip.deadcode.jppass

import java.security.SecureRandom


fun main(args: Array<String>) {

    val config = parse(args)

    when (config.mode) {
        Mode.HELP -> {
            printHelp()
        }
        Mode.VERSION -> {
            println("japanese-password 0.1")
        }
        Mode.PRINT -> {
            val result = generate(config, SecureRandom())
            println(result)
        }
    }
}
