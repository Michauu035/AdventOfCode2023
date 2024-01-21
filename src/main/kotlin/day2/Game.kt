package org.example.day2

import org.w3c.dom.ranges.Range

class Game(private val gameLog: String) {
    val sets: List<GameSet> = getGameSets(getStringSets())
    val id = gameLog.removeRange(gameLog.indexOf(":"), gameLog.length).replace("Game ", "").toInt()

    fun isGamePossible(red: Int, green: Int, blue: Int): Boolean{
        for (set in sets){
            if (set.red > red || set.green > green || set.blue > blue) return false
        }
        return true
    }

    fun minCubes(): GameSet = GameSet(sets.maxOf { it.red }, sets.maxOf { it.green }, sets.maxOf { it.blue })

    private fun getStringSets(): List<String> = gameLog.removeRange(0, gameLog.indexOf(':') + 2).split(";")


    private fun getGameSets(stringSets: List<String>): List<GameSet>{
        val gameSets: MutableList<GameSet> = mutableListOf()
        for(set in stringSets){
            val game = GameSet(0,0,0)
            var temp = set.replace(", ", "");

            while (temp.isNotEmpty()) {
                val foundColor = temp.findAnyOf(listOf("red", "green", "blue"))
                when(foundColor?.second) {
                    "red" -> game.red += temp.substring(0, foundColor.first).trim().toInt()
                    "green" -> game.green += temp.substring(0, foundColor.first).trim().toInt()
                    "blue" -> game.blue += temp.substring(0, foundColor.first).trim().toInt()
                }
                if (foundColor != null) {
                    temp = temp.removeRange(0, foundColor.first + foundColor.second.length)
                }
            }
            gameSets.add(game)
        }
        return gameSets;
    }

    data class GameSet(var red: Int, var green: Int, var blue: Int)
}