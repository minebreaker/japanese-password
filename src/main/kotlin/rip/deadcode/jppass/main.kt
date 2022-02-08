package rip.deadcode.jppass

import com.google.common.io.Resources
import org.apache.commons.cli.HelpFormatter
import rip.deadcode.jppass.Try.Failure
import rip.deadcode.jppass.Try.Success
import java.nio.charset.StandardCharsets
import java.security.SecureRandom


fun main(args: Array<String>) {

    val config = parse(args)

    when (config.mode) {
        Mode.HELP    -> {
            printHelp()
        }
        Mode.VERSION -> {
            printVersion()
        }
        Mode.PRINT   -> {
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

@Suppress("UnstableApiUsage")
private fun getResource(name: String) = Resources.toString(Resources.getResource(name), StandardCharsets.UTF_8).trim()

fun printVersion() {
    val versionStr = getResource("version")
    println(versionStr)
}

fun printHelp() {
    val versionStr = getResource("version")
    val commitHash = getResource("commit")

    println("japanese-password 日本語パスワード生成器")
    println("Version $versionStr, Build $commitHash")
    println("https://github.com/minebreaker/japanese-password")
    println()
    HelpFormatter().printHelp("jppass", options)
}
