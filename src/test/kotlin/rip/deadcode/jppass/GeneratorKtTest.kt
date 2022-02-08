package rip.deadcode.jppass

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import rip.deadcode.jppass.Try.Failure
import java.util.*


internal class GeneratorKtTest {

    @Test
    fun testCheck() {

        var config = Config(Mode.PRINT, 3, hiragana = true, katakana = true, jis2 = true, noNewline = false)
        assertThat(check(config, "あいうえお")).isFalse()
        assertThat(check(config, "アイウエオ")).isFalse()
        assertThat(check(config, "あいうエオ")).isFalse()
        assertThat(check(config, "あいウエ尾")).isTrue()
    }

    @Test
    fun testGenerate() {

        var config = Config(Mode.PRINT, 3, hiragana = true, katakana = false, jis2 = false, noNewline = false)
        var random = Random(3)
        var result = generate(config, random)
        assertThat(result.unsafeGet()).isEqualTo("さもを")

        config = Config(Mode.PRINT, 3, hiragana = true, katakana = true, jis2 = false, noNewline = false)
        random = Random(3)
        result = generate(config, random)
        assertThat(result.unsafeGet()).isEqualTo("さモヲ")

        config = Config(Mode.PRINT, 3, hiragana = true, katakana = true, jis2 = true, noNewline = false)
        random = Random(3)
        result = generate(config, random)
        assertThat(result.unsafeGet()).isEqualTo("ヨや鮮")

        config = Config(Mode.PRINT, 2, hiragana = true, katakana = true, jis2 = true, noNewline = false)
        random = Random(3)
        result = generate(config, random)
        assertThat(result).isInstanceOf(Failure::class.java)

        config = Config(Mode.PRINT, 1, hiragana = true, katakana = true, jis2 = false, noNewline = false)
        random = Random(3)
        result = generate(config, random)
        assertThat(result).isInstanceOf(Failure::class.java)
    }
}
