import utils.readInput

fun main() {
    fun part1(input: List<String>): List<Int> {
        var total = 0
        val totalList = mutableListOf<Int>()
        input.forEach {
            if(it.isEmpty()) {
                totalList.add(total)
                total = 0
            } else {
                total += it.toInt()
            }
        }
        totalList.sortDescending()
        return totalList.subList(0, 3)
    }

    fun part2(input: List<Int>) = input.sum()

    val input = readInput("day01")
    val outputPart1 = part1(input)
    println(outputPart1.first())
    println(part2(outputPart1))
}