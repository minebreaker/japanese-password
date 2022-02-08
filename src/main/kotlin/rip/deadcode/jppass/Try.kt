package rip.deadcode.jppass


sealed interface Try<T, E : Exception> {

    fun unsafeGet(): T

    data class Success<T, E : Exception>(val result: T) : Try<T, E> {
        fun get(): T = result
        override fun unsafeGet(): T = result
    }

    data class Failure<T, E : Exception>(val failure: E) : Try<T, E> {
        fun failure(): E = failure
        override fun unsafeGet(): T = throw UnsupportedOperationException("unsafeGet() for Failure")
    }
}
