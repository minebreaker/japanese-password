package rip.deadcode.jppass

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
    return hiragana.any { c -> s.contains(c) }
            && if (config.katakana) katakana.any { c -> s.contains(c) } else true
}

tailrec fun generate(config: Config, random: Random): String {

    val candidates = hiragana + if (config.katakana) katakana else listOf()

    val result = Stream
        .generate {
            val nth = random.nextInt(candidates.size)
            candidates[nth]
        }
        .limit(config.length.toLong())
        .collect(joining())

    return if (check(config, result)) result else generate(config, random)
}
