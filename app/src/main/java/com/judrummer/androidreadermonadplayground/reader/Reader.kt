package com.judrummer.androidreadermonadplayground.reader

//copy from https://github.com/mercari/ReaderK make it supspendable
class Reader<T : Any, U : Any>(val runReader: suspend (T) -> U) {

    companion object {
        //This is a wrapper over identity function
        fun <T : Any> ask(): Reader<T, T> = Reader { t: T -> t }
    }

    suspend fun <R : Any> local(f: suspend (R) -> T): Reader<R, U> = Reader { r: R -> runReader(f(r)) }
}

//monad
suspend fun <T : Any, Value : Any> Reader.Companion.pure(v: Value): Reader<T, Value> =
        Reader { v }

suspend fun <T : Any, U : Any, R : Any> Reader<T, U>.flatMap(transform: suspend (U) -> Reader<T, R>): Reader<T, R> =
        Reader { t: T -> transform(runReader(t)).runReader(t) }

//functor
suspend fun <T : Any, U : Any, R : Any> Reader<T, U>.map(transform: suspend (U) -> R): Reader<T, R> = Reader { t: T -> transform(runReader(t)) }
