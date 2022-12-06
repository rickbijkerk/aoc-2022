fun main() {

    fun test_startContainers(): Map<Int, ArrayDeque<String>> = mapOf(
        1 to ArrayDeque(listOf("z", "n")),
        2 to ArrayDeque(listOf("m", "c", "d")),
        3 to ArrayDeque(listOf("p"))
    )

    fun startContainers() = mapOf(
        1 to ArrayDeque(listOf("b", "w", "n")),
        2 to ArrayDeque(listOf("l", "z", "s", "p", "t", "d", "m", "b")),
        3 to ArrayDeque(listOf("q", "h", "z", "w", "r")),
        4 to ArrayDeque(listOf("w", "d", "v", "j", "z", "r")),
        5 to ArrayDeque(listOf("s", "h", "m", "b")),
        6 to ArrayDeque(listOf("l", "g", "n", "j", "h", "v", "p", "b")),
        7 to ArrayDeque(listOf("j", "q", "z", "f", "h", "d", "l", "s")),
        8 to ArrayDeque(listOf("w", "s", "f", "j", "g", "q", "b")),
        9 to ArrayDeque(listOf("z", "w", "m", "s", "c", "d", "j"))
    )

    fun part1(input: List<String>, containerRows: Map<Int, ArrayDeque<String>>): String {

        input.forEach { line ->
            val moves = line.substringAfter("move ").substringBefore(" from").toInt()
            val from = line.substringAfter("from ").substringBefore(" to").toInt()
            val to = line.substringAfter("to ").toInt()

            repeat(moves) {
                val removedContainer = containerRows[from]!!.removeLast()
                containerRows[to]!!.addLast(removedContainer)
            }
        }

        return containerRows.values.joinToString(separator = "") { it.last().toUpperCase() }
    }

    fun part2(input: List<String>, containerRows: Map<Int, ArrayDeque<String>>): String {
        input.forEach { line ->
            val moves = line.substringAfter("move ").substringBefore(" from").toInt()
            val from = line.substringAfter("from ").substringBefore(" to").toInt()
            val to = line.substringAfter("to ").toInt()

            val result = (1..moves).map { containerRows[from]!!.removeLast() }.toList().reversed()
            result.map { removedContainer -> containerRows[to]!!.addLast(removedContainer) }
        }

        return containerRows.values.joinToString(separator = "") { it.last().toUpperCase() }
    }

    val testInput = readInput("day05_test")

    // TEST PART 1
    val resultPart1 = part1(testInput, test_startContainers())

    val expectedPart1 = "CMZ" //TODO Change me

    println("testresult:$resultPart1 expected:$expectedPart1 \n")
    check(resultPart1 == expectedPart1)

    //Check results part1
    val input = readInput("day05")
    println("ANSWER 1: ${part1(input, startContainers())}\n\n")


    // TEST PART 2
    val resultPart2 = part2(testInput, test_startContainers())

    val expectedPart2 = "MCD" //TODO Change me

    println("testresult:$resultPart2 expected:$expectedPart2 \n")
    check(resultPart2 == expectedPart2)

    //Check results part2
    println("ANSWER 2: ${part2(input, startContainers())}")
}
