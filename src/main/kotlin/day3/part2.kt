package org.example.day3

fun part2(lines: List<String>): Int {
    val allSymbols: MutableList<Symbol> = mutableListOf()
    val allPartNumbers: MutableList<Number> = mutableListOf()

    // find * symbols and numbers
    for (i in lines.indices) {
        val sym = Regex("[*]").findAll(lines[i]).map { Symbol(y = i, x = it.range.first) }.toList()
        val nums = Regex("\\d+").findAll(lines[i])
            .map { Number(value = it.value.toInt(), xStart = it.range.first, xEnd = it.range.last, y = i) }.toList()
        allPartNumbers.addAll(nums.toTypedArray())
        allSymbols.addAll(sym.toTypedArray())
    }

    // filter symbols that have two adjacent numbers and save these numbers in list
    val filteredSymbols = allSymbols.map { sym ->
        sym.also { symbol ->
            symbol.gears = allPartNumbers
                .filter { it.y in (sym.y - 1..sym.y + 1) }
                .filter { it.inRange(sym.x - 1..sym.x + 1) }
                .map { number -> number.value }
        }
    }.filter { it.gears.size == 2 }

    // get gear ratios
    val sum = filteredSymbols.sumOf { it.gears.reduceOrNull { acc, i -> acc * i } ?: 0 }

    println(sum)
    return sum
}

data class Symbol(val x: Int, val y: Int, var gears: List<Int> = emptyList())

fun Number.inRange(range: IntRange): Boolean {
    return this.xStart in range || this.xEnd in range
}