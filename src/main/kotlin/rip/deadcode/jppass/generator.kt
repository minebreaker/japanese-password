package rip.deadcode.jppass

import rip.deadcode.jppass.JppassException.LengthTooShort
import rip.deadcode.jppass.Try.Failure
import rip.deadcode.jppass.Try.Success
import java.util.*
import java.util.stream.Collectors.joining
import java.util.stream.Stream


val hiragana = listOf(
        "あ", "い", "う", "え", "お",
        "か", "き", "く", "け", "こ",
        "さ", "し", "す", "せ", "そ",
        "た", "ち", "つ", "て", "と",
        "な", "に", "ぬ", "ね", "の",
        "は", "ひ", "ふ", "へ", "ほ",
        "ま", "み", "む", "め", "も",
        "や", "ゆ", "よ",
        "ら", "り", "る", "れ", "ろ",
        "わ", "を", "ん"
)

val katakana = listOf(
        "ア", "イ", "ウ", "エ", "オ",
        "カ", "キ", "ク", "ケ", "コ",
        "サ", "シ", "ス", "セ", "ソ",
        "タ", "チ", "ツ", "テ", "ト",
        "ナ", "ニ", "ヌ", "ネ", "ノ",
        "ハ", "ヒ", "フ", "ヘ", "ホ",
        "マ", "ミ", "ム", "メ", "モ",
        "ヤ", "ユ", "ヨ",
        "ラ", "リ", "ル", "レ", "ロ",
        "ワ", "ヲ", "ン"
)

fun check(config: Config, s: String): Boolean {
    return (if (config.hiragana) hiragana.any { c -> s.contains(c) } else true)
            && (if (config.katakana) katakana.any { c -> s.contains(c) } else true)
            && (if (config.jis2) jis0208.any { c -> s.contains(c) } else true)
}

tailrec fun generate(config: Config, random: Random): Try<String, JppassException> {

    if (!config.satisfiesMinimumRequiredLength()) {
        return Failure(LengthTooShort("出力文字種類に対して、指定された文字列長が短すぎます(length=${config.length})"))
    }

    val candidates = (if (config.hiragana) hiragana else listOf()) +
            (if (config.katakana) katakana else listOf()) +
            (if (config.jis2) jis0208 else listOf())

    val result = Stream
            .generate {
                val nth = random.nextInt(candidates.size)
                candidates[nth]
            }
            .limit(config.length.toLong())
            .collect(joining())

    return if (check(config, result)) Success(result) else generate(config, random)
}

fun Config.satisfiesMinimumRequiredLength(): Boolean {
    fun Boolean.count() = if (this) 1 else 0

    val requiredLength = this.hiragana.count() +
            this.katakana.count() +
            this.jis2.count()

    return this.length >= requiredLength
}
