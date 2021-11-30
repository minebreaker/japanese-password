package rip.deadcode.jppass

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test


internal class ArgKtTest {

    @Test
    fun testParse() {

        var args = arrayOf("-v", "-h")
        var config = parse(args)
        assertThat(config.mode).isEqualTo(Mode.HELP)

        args = arrayOf("--invalid_arg")
        config = parse(args)
        assertThat(config.mode).isEqualTo(Mode.HELP)

        args = arrayOf("-v")
        config = parse(args)
        assertThat(config.mode).isEqualTo(Mode.VERSION)

        args = arrayOf()
        config = parse(args)
        assertThat(config.mode).isEqualTo(Mode.PRINT)
        assertThat(config.length).isEqualTo(16)
        assertThat(config.hiragana).isTrue()
        assertThat(config.katakana).isTrue()
        assertThat(config.jis2).isTrue()

        args = arrayOf("-l", "1", "-k")
        config = parse(args)
        assertThat(config.mode).isEqualTo(Mode.PRINT)
        assertThat(config.length).isEqualTo(1)
        assertThat(config.hiragana).isFalse()
        assertThat(config.katakana).isTrue()
        assertThat(config.jis2).isFalse()

        args = arrayOf("-t", "-jis2")
        config = parse(args)
        assertThat(config.mode).isEqualTo(Mode.PRINT)
        assertThat(config.length).isEqualTo(16)
        assertThat(config.hiragana).isTrue()
        assertThat(config.katakana).isFalse()
        assertThat(config.jis2).isTrue()
    }
}
