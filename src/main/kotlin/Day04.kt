import utils.readInput

fun main() {
    val lines = readInput("day04")

    fun IntRange.containsOrIsContained(range: IntRange) =
        ((this.contains(range.first) && this.contains(range.last)) ||
                (range.contains(this.first) && range.contains(this.last)))

    fun IntRange.itemOverlaps(range: IntRange) =
        ((this.contains(range.first) || this.contains(range.last)) ||
                (range.contains(this.first) || range.contains(this.last)))

    fun extractRange(line: String): Pair<IntRange, IntRange> {
        val (section1, section2) = line.split(",")
        return IntRange(
            section1.split("-").first().toInt(),
            section1.split("-").last().toInt()
        ) to IntRange(
            section2.split("-").first().toInt(),
            section2.split("-").last().toInt()
        )
    }

    fun part1(sections: List<String>): Int {
        var totalFullyContained = 0
        sections.forEach { pairSection ->
            val (range1, range2) = extractRange(pairSection)
            if (range1.containsOrIsContained(range2)) {
                totalFullyContained++
            }
        }
        return totalFullyContained
    }

    fun part2(sections: List<String>): Int {
        var totalOverlapAtAll = 0
        sections.forEach { pairSection ->
            val (range1, range2) = extractRange(pairSection)

            if (range1.itemOverlaps(range2)) {
                totalOverlapAtAll++
            }
        }
        return totalOverlapAtAll
    }

    println("part1: ${part1(lines)}")
    println("part2: ${part2(lines)}")
}