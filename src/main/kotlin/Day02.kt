import utils.readInput

fun main() {
    val lines = readInput("day02")

    fun findWhatWasPlayed(code: String) = Game.values().find { it.code.contains(code) }!!

    fun calcScore(opponentPlay: Game, mePlay: Game) = if(opponentPlay.code == mePlay.code) {
        GameIntendedResult.DRAW.score + mePlay.score
    } else if(opponentPlay.beatsCode == mePlay.code) {
        GameIntendedResult.LOSS.score + mePlay.score
    } else { //I win
        GameIntendedResult.WIN.score + mePlay.score
    }

    fun part1(input: List<Pair<String, String>>) : Int {
        var totalScore = 0
        input.forEach { round ->
            val opponentPlayer = findWhatWasPlayed(round.first)
            val mePlayer = findWhatWasPlayed(round.second)
            totalScore += calcScore(opponentPlayer, mePlayer)
        }
        return totalScore
    }

    fun part2(input: List<Pair<String, String>>) : Int {
        var totalScore = 0
        input.forEach { round ->
            val opponentPlayer = findWhatWasPlayed(round.first)
            val mePlayer = when(GameIntendedResult.values().find { it.code == round.second }!!) {
                GameIntendedResult.DRAW -> opponentPlayer
                GameIntendedResult.LOSS -> findWhatWasPlayed(opponentPlayer.beatsCode)
                else -> Game.values().find { it.beatsCode == opponentPlayer.code }!!
            }

            totalScore += calcScore(opponentPlayer, mePlayer)
        }
        return totalScore
    }

    val strategy = lines.map {
        val split = it.split(" ")
        split.first() to split.last()
    }

    println(part1(strategy))
    println(part2(strategy))
}


enum class GameIntendedResult(val score: Int, val code: String) {
    LOSS(0, "X"),
    DRAW(3, "Y"),
    WIN(6, "Z")
}

enum class Game(val code: String, val score: Int, val beatsCode: String) {
    ROCK("AX", 1, "CZ"),
    PAPER("BY", 2, "AX"),
    SCISSORS("CZ", 3, "BY");
}