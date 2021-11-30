package rip.deadcode.jppass

import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Options


val parser: CommandLineParser = DefaultParser()

val options: Options = Options()
    .addOption("l", "length", true, "出力する文字数")
    .addOption("t", false, "平仮名を含める")
    .addOption("k", false, "片仮名を含める")
    .addOption("jis2", "jis0208", false, "漢字(JIS 0208)を含める")
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
    val hiragana: Boolean,
    val katakana: Boolean,
    val jis2: Boolean
)

fun parse(args: Array<String>): Config {

    try {
        val command = parser.parse(options, args)
        val lengthStr = command.getOptionValue("l")
        val length = lengthStr?.toInt() ?: 16
        val hiraganaOpt = command.hasOption("t")
        val katakanaOpt = command.hasOption("k")
        val jis2Opt = command.hasOption("jis2")

        val enableAll = !hiraganaOpt && !katakanaOpt && !jis2Opt
        val hiragana = hiraganaOpt || enableAll
        val katakana = katakanaOpt || enableAll
        val jis2 = jis2Opt || enableAll
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
            hiragana,
            katakana,
            jis2
        )

    } catch (e: Exception) {
        return Config(
            Mode.HELP,
            0,
            false,
            false,
            false
        )
    }
}

fun printHelp() {
    HelpFormatter().printHelp("jppass", options)
}
