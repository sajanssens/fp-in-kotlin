package nl.bramjanssens.containers

import arrow.core.compose

// Function composition is faster than two mappings
// You can use arrow's compose for it.

fun main() {
    val list = listOf(1, 2, 3)

    val map1 = list.map { it * 2 }.map { it + 1 } // expensive
    val map2 = list.map { (it * 2) + 1 } // faster
    val map3 = list.map({ x: Int -> x + 1 } compose { it * 2 }) // same

    println(map1)
    println(map2)
    println(map3)

    val f: (Int) -> Int = { it * 2 }
    val g: (Int) -> Int = { it + 1 }

    val map4 = list.map(f).map(g) // expensive
    val map5 = list.map { g(f(it)) } // faster
    val map6 = list.map(g compose f) // same

    println(map4)
    println(map5)
    println(map6)
}
