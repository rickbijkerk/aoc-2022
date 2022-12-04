fun main() {

    fun part1(input: List<String>): Int {
        val elves = input.filter { line ->
            val elfOne = line.split(",")[0].let { it.split("-")[0].toInt() to it.split("-")[1].toInt() }
            val elfTwo = line.split(",")[1].let { it.split("-")[0].toInt() to it.split("-")[1].toInt() }

            elfOne.first >= elfTwo.first && elfOne.second <= elfTwo.second ||
                elfTwo.first >= elfOne.first && elfTwo.second <= elfOne.second

        }
        return elves.size
    }

    fun part2(input: List<String>): Int {
        val elves = input.filter { line ->
            val elfOne = line.split(",")[0].let { it.split("-")[0].toInt() to it.split("-")[1].toInt() }
            val elfTwo = line.split(",")[1].let { it.split("-")[0].toInt() to it.split("-")[1].toInt() }

            elfOne.first in elfTwo.first..elfTwo.second|| elfOne.second in elfTwo.first..elfTwo.second||
                elfTwo.first in elfOne.first..elfOne.second|| elfTwo.second in elfOne.first..elfOne.second


        }
        return elves.size
    }

    val testInput = readInput("day04_test")
    // test part1
    val resultPart1 = part1(testInput)

    val expectedPart1 = 2 //TODO Change me

    println("Part1\nresult: $resultPart1")
    println("expected:$expectedPart1 \n")
    check(resultPart1 == expectedPart1)

    //Check results part1
    val input = readInput("day04")
    println("Part1 ${part1(input)}\n\n")

    // test part2
    val resultPart2 = part2(testInput)

    val expectedPart2 = 4 //TODO Change me

    println("Part2\nresult: $resultPart2")
    println("expected:$expectedPart2 \n")
    check(resultPart2 == expectedPart2)

    //Check results part2
    println("Part2 ${part2(input)}")
}
