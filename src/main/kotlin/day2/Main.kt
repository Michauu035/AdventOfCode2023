package org.example.day2

import org.example.readFile

fun main(){
    val game = Game("Game 23: 3 red, 5 green, 1 red; 10 blue, 1 green, 9 red; 2 red, 10 green, 9 blue; 9 blue, 7 green; 2 red, 7 blue")
    val games: List<Game> = readFile("src/main/kotlin/day2/input.txt").map { Game(it) }.toList()
    println(
        games.filter { it.isGamePossible(12, 13, 14) }.sumOf { it.id }
    )
    println(games.sumOf { it.minCubes().red * it.minCubes().green * it.minCubes().blue })
}
