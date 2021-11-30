package rip.deadcode.jppass

import org.apache.commons.cli.HelpFormatter
import java.security.SecureRandom


fun main(args: Array<String>) {

    val config = parse(args)

    when (config.mode) {
        Mode.HELP -> {
            printHelp()
        }
        Mode.VERSION -> {
            println("japanese-password 0.2")
        }
        Mode.PRINT -> {
            val result = generate(config, SecureRandom())
            println(result)
        }
    }
}

fun printHelp() {
    println("japanese-password 日本語パスワード生成器")
    println("https://github.com/minebreaker/japanese-password")
    println()
    HelpFormatter().printHelp("jppass", options)
}
