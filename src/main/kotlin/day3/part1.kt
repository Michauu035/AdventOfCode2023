package org.example.day3

fun part1(lines: List<String>) {
    val symbolRegex = Regex("[^0-9.]")
    val allNumbers = mutableListOf<Number>()
    val validNumbers = mutableListOf<Int>()

    for (i in lines.indices) {
        val nums = Regex("\\d+").findAll(lines[i]).map {
            Number(
                value = lines[i].substring(it.range.first, it.range.last + 1).toInt(),
                xStart = it.range.first,
                xEnd = it.range.last,
                y = i
            )
        }.toMutableList()
        allNumbers.addAll(nums.toTypedArray())
    }
    for (num in allNumbers) {
        if (lines.checkProximity(num.y, num.xStart, num.xEnd, symbolRegex)) {
            validNumbers.add(num.value)
        }
    }
    println(validNumbers.sum())
}

fun List<String>.checkProximity(y: Int, xPos1: Int, xPos2: Int, regex: Regex): Boolean {
    val lineLength = this[0].length - 1
    val yStart = (y - 1).coerceAtLeast(0)
    val yEnd = (y + 1).coerceAtMost(this.size - 1)
    val xStart = (xPos1 - 1).coerceAtLeast(0)
    val xEnd = (xPos2 + 1).coerceAtMost(lineLength)
    val area = mutableListOf<String>()
    for (i in yStart..yEnd) {
        val line = this[i].substring(xStart..xEnd)
        area.add(line)
    }

    return area.joinToString("").contains(regex)
}

data class Number(val value: Int, val xStart: Int, val xEnd: Int, val y: Int)
