fun main() {

    fun part1(input: List<String>): Int {
        val line = input.first()

        val result = (0..line.length - 4).first { index ->
            line.substring(index, index + 4).toSet().size == 4
        }

        return result + 4
    }

    fun part2(input: List<String>): Int {
        val line = input.first()

        val result = (0..line.length - 14).first { index ->
            line.substring(index, index + 14).toSet().size == 14
        }

        return result + 14
    }

    val testInput = readInput("day06_test")

    // TEST PART 1
    val resultPart1 = part1(testInput)

    val expectedPart1 = 11 //TODO Change me

    println("testresult:$resultPart1 expected:$expectedPart1 \n")
    check(resultPart1 == expectedPart1)

    //Check results part1
    val input = readInput("day06")
    println("ANSWER 1: ${part1(input)}")

    // TEST PART 2
    val resultPart2 = part2(testInput)

    val expectedPart2 = 26 //TODO Change me

    println("testresult:$resultPart2 expected:$expectedPart2 \n")
    check(resultPart2 == expectedPart2)

    //Check results part2
    println("ANSWER 2: ${part2(input)}")
}
