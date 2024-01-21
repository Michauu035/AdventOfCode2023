package org.example

import kotlin.io.path.Path

fun main() {
    println("Hello World!")
}

fun readFile(filename: String): List<String>{
    return Path(filename).toFile().useLines { it.toList() }
}
