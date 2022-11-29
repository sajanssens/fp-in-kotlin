package nl.bramjanssens.containers

sealed interface LinkedList<out A>

object End : LinkedList<Nothing>
data class Cell<out A>(val value: A, val next: LinkedList<A>) : LinkedList<A>

interface LinkedListVisitor<A, R> {
    fun visitEnd(): R
    fun visitCell(value: A, next: LinkedList<A>): R
}

fun <A, R> LinkedList<A>.visit(visitor: LinkedListVisitor<A, R>): R =
    when (this) {
        is End -> visitor.visitEnd()
        is Cell -> visitor.visitCell(value, next)
    }

interface LinkedListRecursiveVisitor<A, R> {
    fun visitEnd(): R
    fun visitCell(value: A, nextResult: R): R
}

fun <A, R> LinkedList<A>.recursiveVisit(visitor: LinkedListRecursiveVisitor<A, R>): R =
    when (this) {
        is End -> visitor.visitEnd()
        is Cell -> visitor.visitCell(value, next.recursiveVisit(visitor))
    }

fun LinkedList<Int>.sumAll(): Int =
    recursiveVisit(object : LinkedListRecursiveVisitor<Int, Int> {
        override fun visitEnd() = 0
        override fun visitCell(value: Int, nextResult: Int) = value + nextResult
    })

fun <A, R> LinkedList<A>.fold(
    end: () -> R,
    cell: (A, R) -> R
): R {
    TODO()
}

fun main() {
    println(listOf(1, 2).fold(0) { acc, i -> acc + i })
}
