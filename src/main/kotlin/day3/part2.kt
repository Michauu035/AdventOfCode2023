package org.example.day3

fun part2(lines: List<String>): Int {
    val allSymbols: MutableList<Symbol> = mutableListOf()
    // find * symbols
    for (i in lines.indices) {
        val sym = Regex("[*]").findAll(lines[i]).map { Symbol(y = i, x = it.range.first) }.toList()
        allSymbols.addAll(sym.toTypedArray())
    }
    // check proximity

    val filteredSymbols = allSymbols.filter { lines.hasTwoConnectedNumbers(it.y, it.x) }

    val sum = filteredSymbols.map { lines.multiplyConnectedNumbers(it.y, it.x) }.reduceOrNull {acc, i -> acc + i} ?: 0
    println(sum)
    return sum
}

fun List<String>.hasTwoConnectedNumbers(y: Int, x: Int): Boolean {
    val lineLength = this[0].length
    val yStart = (y - 1).coerceAtLeast(0)
    val yEnd = (y+1).coerceAtMost(this.size - 1)
    val xStart = (x - 1).coerceAtLeast(0)
    val xEnd = (x + 1).coerceAtMost(lineLength - 1)
    val area: MutableList<String> = mutableListOf()

    // find connection point between two numbers
    for (i in yStart..yEnd) {
        area.add(this[i].substring(xStart..(xEnd)))
    }
    val joined = area.joinToString(".").replace(Regex("[^0-9]+"), ".") + ";"
    return joined.matches(Regex("[.]*\\d+[.]\\d+[.]*;"))
}

fun List<String>.multiplyConnectedNumbers(y: Int, x: Int): Int {
    val lineLength = this[0].length
    val yStart = (y - 1).coerceAtLeast(0)
    val yEnd = (y+1).coerceAtMost(this.size - 1)
    val xStart = (x - 1).coerceAtLeast(0)
    val xEnd = (x + 1).coerceAtMost(lineLength - 1)
    val area: MutableList<String> = mutableListOf()
    val foundNumbers: MutableList<Int> = mutableListOf()

    for (i in yStart..yEnd) {
        area.add(this[i].substring(xStart..(xEnd)))
    }


    for (a in area.indices) {
        area[a] = area[a].replace("*", "[*]")
        val regex: Regex = Regex("\\d+")
        val foundAll = regex.findAll(this[a + yStart]).toList()
        val num = foundAll.filter {
            xStart in it.range || xEnd in it.range
        }.map { it.value.toInt() }

        foundNumbers.addAll(num.toTypedArray())
    }


    return if(foundNumbers.size == 2) foundNumbers.reduceOrNull { acc, i -> acc * i} ?: 0 else 0
}

data class Symbol(val x: Int, val y: Int)