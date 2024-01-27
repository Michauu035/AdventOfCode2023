package day3

import org.example.day3.part1
import org.example.day3.part2
import org.example.readFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day3 {
    private val lines = readFile("src/main/kotlin/day3/example.txt")

    @Test
    fun part1(){
        assertEquals(4361, part1(lines))
    }
    @Test
    fun part2(){
        assertEquals(467835, part2(lines))
    }
}