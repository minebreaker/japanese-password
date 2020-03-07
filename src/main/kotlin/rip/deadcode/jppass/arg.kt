package rip.deadcode.jppass

import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Options


val parser: CommandLineParser = DefaultParser()

val options: Options = Options()
        .addOption("l", "length", true, "出力する文字数")
        .addOption("k", false, "片仮名を含める")
//        .addOption("c", false, "漢字を含める")
//        .addOption("s", false, "小文字を含める")
        .addOption("v", "version", false, "バージョン情報")
        .addOption("h", "help", false, "ヘルプ")

enum class Mode {
    PRINT,
    VERSION,
    HELP
}

data class Config(
        val mode: Mode,
        val length: Int,
        val katakana: Boolean,
        val kanji: Boolean
)

fun parse(args: Array<String>): Config {

    try {
        val command = parser.parse(options, args)
        val lengthStr = command.getOptionValue("l")
        val length = lengthStr?.toInt() ?: 16
        val katakana = command.hasOption("k")
        val kanji = command.hasOption("c")
        val version = command.hasOption("v")
        val help = command.hasOption("h")

        val mode = when {
            help -> Mode.HELP
            version -> Mode.VERSION
            else -> Mode.PRINT
        }

        return Config(
                mode,
                length,
                katakana,
                kanji
        )

    } catch (e: Exception) {
        return Config(
                Mode.HELP,
                0,
                false,
                false
        )
    }
}

fun printHelp() {
    HelpFormatter().printHelp("jppass", options)
}
