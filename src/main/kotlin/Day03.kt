import utils.readInput
import javax.print.attribute.standard.JobPriority

fun main() {
    val lines = readInput("day03")

    fun part1(rucksackItems: List<String>, priority: List<Char>): Int {
        var totalPriority = 0
        rucksackItems.forEach {
            val compartments = it.substring(0, it.length/2) to it.substring(it.length/2)
            val duplicated = compartments.first.find { letter -> compartments.second.contains(letter) }

            totalPriority += priority.indexOf(duplicated)+1
        }

        return totalPriority
    }

    val chars = ('a'..'z').toMutableList().apply {
        this.addAll(('A'..'Z').toList())
    }.toList()

    println(part1(lines, chars))
}