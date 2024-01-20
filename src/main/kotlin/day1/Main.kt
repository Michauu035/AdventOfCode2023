package day1

import java.io.BufferedReader
import java.io.File
import java.nio.file.Paths
import kotlin.io.path.Path

fun main(){
    println(
        getNumbers(
            readFile("src/main/kotlin/day1/input.txt")
        ).sum()
    )
}

fun getNumbers(list: List<String>): List<Int> {
    return list.map {
        it
            .replaceTextWithNumber()
            .replace(Regex("[a-zA-Z]"), "")
            .getFirstAndLast()
            .toInt()
    }
}

fun readFile(filename: String): List<String>{
    return Path(filename).toFile().useLines { it.toList() }
}

fun String.getFirstAndLast(): String = "${first()}${last()}"

fun String.replaceTextWithNumber(): String {
    val firstOccurence = this.findAnyOf(listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine"))
    val lastOccurence = this.findLastAnyOf(listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine"))
    val map = mapOf(
        Pair("one", "1"),
        Pair("two", "2"),
        Pair("three", "3"),
        Pair("four", "4"),
        Pair("five", "5"),
        Pair("six", "6"),
        Pair("seven", "7"),
        Pair("eight", "8"),
        Pair("nine", "9")
    )

    var text = this;

    if (firstOccurence != null) {
        text = text
            .replaceRange(firstOccurence.first, firstOccurence.first + 1,map[firstOccurence.second] ?: "" )
    }
    if (lastOccurence != null) {
        text = text
            .replaceRange(lastOccurence.first, lastOccurence.first + 1,map[lastOccurence.second] ?: "" )
    }

    return text;
}