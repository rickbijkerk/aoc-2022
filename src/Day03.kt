fun main() {

    fun part1(input: List<String>): Int {
        val alphabet = "abcdefghijklmnopqrstuvwxyz"

        val duplicateLetters = input.map { line ->
            val part1 = line.substring(0, line.length / 2)
            val part2 = line.substring(line.length / 2, line.length)

            part1.toCharArray().toList().intersect(part2.toCharArray().toList()).first()
        }.map { letter ->
            var number = alphabet.indexOf(letter, ignoreCase = true) +1
            if (letter.isUpperCase()) {
                number += 26
            }
            number
        }

        return duplicateLetters.sum()
    }

    fun part2(input: List<String>): Int {
        val alphabet = "abcdefghijklmnopqrstuvwxyz"

        val setsOfThree = input.map { it.toCharArray().toList() }.chunked(3)

        val result = setsOfThree.map {setOfThree ->
            setOfThree[0].intersect(setOfThree[1]).intersect(setOfThree[2]).first()
        }.map { letter ->
            var number = alphabet.indexOf(letter, ignoreCase = true) +1
            if (letter.isUpperCase()) {
                number += 26
            }
            number
        }

        return result.sum()
    }

    val testInput = readInput("day03_test")
    // test part1
    val resultPart1 = part1(testInput)

    val expectedPart1 = 157 //TODO Change me

    println("Part1\nresult: $resultPart1")
    println("expected:$expectedPart1 \n")
    check(resultPart1 == expectedPart1)

    //Check results part1
    val input = readInput("day03")
    println("Part1 ${part1(input)}\n\n")

    // test part2
    val resultPart2 = part2(testInput)

    val expectedPart2 = 70 //TODO Change me

    println("Part2\nresult: $resultPart2")
    println("expected:$expectedPart2 \n")
    check(resultPart2 == expectedPart2)

    //Check results part2
    println("Part2 ${part2(input)}")
}
