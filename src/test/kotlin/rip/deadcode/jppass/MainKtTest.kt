package rip.deadcode.jppass

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.regex.Pattern

class MainKtTest {

    @Test
    fun test() {
        // Repeat for the randomness
        repeat(100) {
            var result = redirectInput {
                main(arrayOf("-t", "-n"))
            }
            assertThat(result).matches("^[あ-ん]{16}$")

            result = redirectInput {
                main(arrayOf("-k", "-l", "10"))
            }
            // Note that line break is platform-dependent
            assertThat(result).matches(Pattern.compile("^[ア-ン]{10}\\r?\\n$"))

            result = redirectInput {
                main(arrayOf("-k", "-t"))
            }
            assertThat(result).matches(Pattern.compile("^[あ-んア-ン]{16}\\r?\\n$"))

            result = redirectInput {
                main(arrayOf("-l", "8"))
            }
            assertThat(result).matches(Pattern.compile("^.{8}\\r?\\n$"))
        }
    }


    private fun redirectInput(block: () -> Unit): String {

        val current = System.out
        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        System.setOut(ps)

        try {
            block()
        } catch (e: Throwable) {
            throw RuntimeException(e)
        }

        System.setOut(current)

        return String(baos.toByteArray(), java.nio.charset.StandardCharsets.UTF_8)
    }
}
