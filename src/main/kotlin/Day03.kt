import utils.readInput

fun main() {
    val lines = readInput("day03")

    fun commonLetter(str1: String, str2: String, str3: String? = null): Char =
        str1.find { letter -> str2.contains(letter) && (str3?.contains(letter) ?: true) }!!

    fun part1(rucksackItems: List<String>, priority: List<Char>): Int {
        var totalPriority = 0
        rucksackItems.forEach {
            val compartments = it.substring(0, it.length/2) to it.substring(it.length/2)
            val duplicated = commonLetter(compartments.first, compartments.second)

            totalPriority += priority.indexOf(duplicated)+1
        }

        return totalPriority
    }

    var totalPriority = 0
    fun part2(ruckSackItems: List<String>, priority: List<Char>): Int {
        for (i in ruckSackItems.indices step 3) {
            val elf1 = ruckSackItems[i]
            val elf2 = ruckSackItems[i+1]
            val elf3 = ruckSackItems[i+2]

            val commonItem = commonLetter(elf1, elf2, elf3)

            totalPriority += priority.indexOf(commonItem)+1
        }
        return totalPriority
    }

    val chars = ('a'..'z').toMutableList().apply {
        this.addAll(('A'..'Z').toList())
    }.toList()

    println("part1: ${part1(lines, chars)}")
    println("part2: ${part2(lines, chars)}")
}