package rip.deadcode.jppass

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*


internal class GeneratorKtTest {

    @Test
    fun testCheck() {

        var config = Config(Mode.PRINT, 3, true, false)
        assertThat(check(config, "あいうえお")).isFalse()
        assertThat(check(config, "アイウエオ")).isFalse()
        assertThat(check(config, "あいうエオ")).isTrue()
    }

    @Test
    fun testGenerate() {

        var config = Config(Mode.PRINT, 3, false, false)
        var random = Random(3)
        var result = generate(config, random)
        assertThat(result).isEqualTo("さもを")

        config = Config(Mode.PRINT, 3, true, false)
        random = Random(3)
        result = generate(config, random)
        assertThat(result).isEqualTo("さモヲ")
    }
}
