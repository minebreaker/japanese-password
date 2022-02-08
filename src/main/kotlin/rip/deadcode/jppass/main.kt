package rip.deadcode.jppass

import org.apache.commons.cli.HelpFormatter
import rip.deadcode.jppass.Try.Failure
import rip.deadcode.jppass.Try.Success
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
            when (val result = generate(config, SecureRandom())) {
                is Success ->
                    if (config.noNewline) {
                        print(result.get())
                    } else {
                        println(result.get())
                    }
                is Failure ->
                    System.err.println(result.failure.message)
            }
        }
    }
}

fun printHelp() {
    println("japanese-password 日本語パスワード生成器")
    println("https://github.com/minebreaker/japanese-password")
    println()
    HelpFormatter().printHelp("jppass", options)
}
