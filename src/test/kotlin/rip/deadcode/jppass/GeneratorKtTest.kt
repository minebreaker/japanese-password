package rip.deadcode.jppass

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*


internal class GeneratorKtTest {

    @Test
    fun testCheck() {

        var config = Config(Mode.PRINT, 3, katakana = true, kanji = true)
        assertThat(check(config, "あいうえお")).isFalse()
        assertThat(check(config, "アイウエオ")).isFalse()
        assertThat(check(config, "あいうエオ")).isFalse()
        assertThat(check(config, "あいウエ尾")).isTrue()
    }

    @Test
    fun testGenerate() {

        var config = Config(Mode.PRINT, 3, katakana = false, kanji = false)
        var random = Random(3)
        var result = generate(config, random)
        assertThat(result).isEqualTo("さもを")

        config = Config(Mode.PRINT, 3, katakana = true, kanji = false)
        random = Random(3)
        result = generate(config, random)
        assertThat(result).isEqualTo("さモヲ")

        config = Config(Mode.PRINT, 3, katakana = true, kanji = true)
        random = Random(3)
        result = generate(config, random)
        assertThat(result).isEqualTo("ヨや鮮")
    }
}
