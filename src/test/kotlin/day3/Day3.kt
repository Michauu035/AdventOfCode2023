package day3

import org.example.day3.hasTwoConnectedNumbers
import org.example.day3.multiplyConnectedNumbers
import org.example.day3.part1
import org.example.day3.part2
import org.example.readFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day3 {
    private val example1 = readFile("src/main/kotlin/day3/example.txt")
    private val example2 = readFile("src/test/kotlin/day3/example2.txt")
    private val example3 = readFile("src/test/kotlin/day3/example3.txt")
    private val example4 = readFile("src/test/kotlin/day3/example4.txt")

    @Test
    fun part1(){
        assertEquals(4361, part1(example1))
    }
    @Test
    fun part2(){
        assertEquals(true, "7.*.35.".matches(Regex(".*\\d+[.*]+\\d+.*")))
        assertEquals(true, listOf("2*2").hasTwoConnectedNumbers(0, 1))
        assertEquals(4, listOf("2*2").multiplyConnectedNumbers(0,1))
        assertEquals(467835, part2(example1))
        assertEquals(20000, part2(example2))
        assertEquals(6756, part2(example3))
        assertEquals(64, part2(example4))
    }
}