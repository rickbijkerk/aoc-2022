fun main() {

    fun part1(input: List<String>): Int {
        val draw = 3
        val win = 6

        val choiceValue = mapOf(
            "A" to 1,
            "B" to 2,
            "C" to 3,
        )

        val result = input.map {
            val opponentPlay = it.split(" ")[0]
            val myChoice = it.split(" ")[1].let { char ->
                when (char) {
                    "X" -> return@let "A"
                    "Y" -> return@let "B"
                    "Z" -> return@let "C"
                    else -> {
                        throw Exception()
                    }
                }
            }

            if (opponentPlay == myChoice) {
                draw + choiceValue[myChoice]!!
            } else if (opponentPlay == "A" && myChoice == "B") {
                win + choiceValue[myChoice]!!
            } else if (opponentPlay == "B" && myChoice == "C") {
                win + choiceValue[myChoice]!!
            } else if (opponentPlay == "C" && myChoice == "A") {
                win + choiceValue[myChoice]!!
            } else {
                choiceValue[myChoice]!!
            }
        }

        return result.sum()
    }

    fun lose(opponentPlay: String): String {
        return when (opponentPlay) {
            "B" -> "A"
            "C" -> "B"
            "A" -> "C"
            else -> throw Exception()
        }
    }

    fun win(opponentPlay: String): String {
        return when (opponentPlay) {
            "A" -> "B"
            "B" -> "C"
            "C" -> "A"
            else -> throw Exception()
        }
    }

    fun part2(input: List<String>): Int {
        val draw = 3
        val win = 6

        val choiceValue = mapOf(
            "A" to 1,
            "B" to 2,
            "C" to 3,
        )

        val result = input.map {
            val opponentPlay = it.split(" ")[0]

            val outcome = it.split(" ")[1]

            val myChoice = outcome.let { char ->
                when (char) {
                    "X" -> return@let lose(opponentPlay)
                    "Y" -> return@let opponentPlay
                    "Z" -> return@let win(opponentPlay)
                    else -> throw Exception()
                }
            }

            when (outcome) {
                "Y" -> choiceValue[myChoice]!! + draw
                "X" -> choiceValue[myChoice]!!
                "Z" -> choiceValue[myChoice]!! + win
                else -> throw Exception()
            }
        }

        return result.sum()
    }

    val testInput = readInput("Day02_test")
    // test part1
    val resultPart1 = part1(testInput)

    val expectedPart1 = 15 //Change me

    println("Part1\nresult: $resultPart1")
    println("expected:$expectedPart1 \n")
    check(resultPart1 == expectedPart1)


    //Check results part1
    val input = readInput("Day02")
    println("Part1 ${part1(input)}\n\n")


    // test part2
    val resultPart2 = part2(testInput)

    val expectedPart2 = 12 //Change me

    println("Part2\nresult: $resultPart2")
    println("expected:$expectedPart2 \n")
    check(resultPart2 == expectedPart2)


    //Check results part2
    println("Part2 ${part2(input)}")
}
