package rip.deadcode.jppass

import java.io.Serial


sealed class JppassException(message: String) : Exception(message) {

    class LengthTooShort(message: String) : JppassException(message) {
        companion object {
            @Serial
            private const val serialVersionUID: Long = -1L
        }
    }

    companion object {
        // We don't care serialization
        @Serial
        private const val serialVersionUID: Long = -1L
    }
}
