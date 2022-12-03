fun main() {

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("day03_test")
    // test part1
    val resultPart1 = part1(testInput)

    val expectedPart1 = 15 //TODO Change me

    println("Part1\nresult: $resultPart1")
    println("expected:$expectedPart1 \n")
    check(resultPart1 == expectedPart1)


    //Check results part1
    val input = readInput("day03")
    println("Part1 ${part1(input)}\n\n")


    // test part2
    val resultPart2 = part2(testInput)

    val expectedPart2 = 12 //TODO Change me

    println("Part2\nresult: $resultPart2")
    println("expected:$expectedPart2 \n")
    check(resultPart2 == expectedPart2)


    //Check results part2
    println("Part2 ${part2(input)}")
}
