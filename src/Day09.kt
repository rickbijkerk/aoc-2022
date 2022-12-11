fun main() {

    fun isTouching(headCoordinates: Pair<Int, Int>, tailCoordinate: Pair<Int, Int>): Boolean {
        val xOffset = headCoordinates.first - tailCoordinate.first
        val yOffset = headCoordinates.second - tailCoordinate.second

        // X touching
        if (xOffset == 1 && yOffset == 0 || xOffset == -1 && yOffset == 0) {
            return true
            // Y touching
        } else if (yOffset == 1 && xOffset == 0 || yOffset == -1 && xOffset == 0) {
            return true
        } else if (arrayOf(-1, 1).contains(xOffset) && arrayOf(-1, 1).contains(yOffset)) {
            return true
        } else if (headCoordinates == tailCoordinate) {
            return true
        }
        return false
    }

    fun moveHead(direction: String, headCoordinates: Pair<Int, Int>): Pair<Int, Int> {
        var newHeadCoordinate = headCoordinates
        when (direction) {
            "R" -> newHeadCoordinate = headCoordinates.first + 1 to headCoordinates.second
            "L" -> newHeadCoordinate = headCoordinates.first - 1 to headCoordinates.second
            "U" -> newHeadCoordinate = headCoordinates.first to headCoordinates.second + 1
            "D" -> newHeadCoordinate = headCoordinates.first to headCoordinates.second - 1
        }
        return newHeadCoordinate
    }

    fun moveTail(
        tailCoordinate: Pair<Int, Int>,
        headCoordinates: Pair<Int, Int>,
    ): Pair<Int, Int> {
        var resultTail = tailCoordinate
        val yDiff = headCoordinates.second - resultTail.second
        val xDiff = headCoordinates.first - resultTail.first

        if (resultTail.first == headCoordinates.first) {
            resultTail = resultTail.first to resultTail.second + (yDiff / 2)
        } else if (resultTail.second == headCoordinates.second) {
            resultTail = resultTail.first + (xDiff / 2) to resultTail.second
        }
        // Diagonally move
        else if (xDiff == 1 || xDiff == -1) {
            resultTail = resultTail.first + xDiff to resultTail.second + (yDiff / 2)
        } else if (yDiff == 1 || yDiff == -1) {
            resultTail = resultTail.first + (xDiff / 2) to resultTail.second + yDiff
        } else if (arrayOf(-2, 2).contains(xDiff) && arrayOf(-2, 2).contains(yDiff)) {
            resultTail = resultTail.first + (xDiff / 2) to resultTail.second + (yDiff / 2)
        }

        return resultTail
    }

    fun part1(moves: List<String>): Int {
        val coordinates = mutableSetOf<Pair<Int, Int>>()

        var headCoordinates = 0 to 0
        var tailCoordinate = 0 to 0

        coordinates.add(tailCoordinate)

        moves.forEach { line ->
            val direction = line.split(" ")[0]
            val steps = line.split(" ")[1].toInt()

            repeat((1..steps).count()) {
                headCoordinates = moveHead(direction, headCoordinates)

                if (!isTouching(headCoordinates, tailCoordinate)) {
                    tailCoordinate = moveTail(tailCoordinate, headCoordinates)
                }
                coordinates.add(tailCoordinate)
            }
        }

        return coordinates.size
    }

    fun prettyPrint(
        steps: Int,
        direction: String,
        tailCoordinates: MutableList<Pair<Int, Int>>,
        headCoordinates: Pair<Int, Int>
    ) {
        println(" Moved $steps to $direction")
        (20 downTo -20).forEach { y ->
            (-20..20).forEach { x ->
                if (y == 0 && x == 0) {
                    print(" S")
                } else {
                    val index = tailCoordinates.indexOf(x to y)

                    if (index != -1) {
                        print(" ${index + 1}")
                    } else if (y == headCoordinates.second && x == headCoordinates.first) {
                        print(" H")
                    } else {
                        print(" .")
                    }
                }
            }
            println()
        }
        println()
        println()
        println()
    }

    fun printResult(coordinates: MutableSet<Pair<Int, Int>>) {
        val size = 20
        (size downTo size * -1).forEach { y ->
            (size * -1..size).forEach { x ->
                if (y == 0 && x == 0) {
                    print(" S")
                } else {
                    val index = coordinates.indexOf(x to y)

                    if (index != -1) {
                        print(" #")
                    } else {
                        print(" .")
                    }
                }
            }
            println()
        }
    }

    fun part2(moves: List<String>): Int {
        val coordinates = mutableSetOf<Pair<Int, Int>>()

        var headCoordinates = 0 to 0

        val tailCoordinates = mutableListOf(
            0 to 0,
            0 to 0,
            0 to 0,
            0 to 0,
            0 to 0,
            0 to 0,
            0 to 0,
            0 to 0,
            0 to 0,
        )
        coordinates.add(tailCoordinates.first())

        moves.forEach { line ->
            val direction = line.split(" ")[0]
            val steps = line.split(" ")[1].toInt()

            repeat((1..steps).count()) {

                headCoordinates = moveHead(direction, headCoordinates)

                tailCoordinates.forEachIndexed { index, knot ->
                    val headKnot = if (index == 0) {
                        headCoordinates
                    } else {
                        tailCoordinates[index - 1]
                    }

                    if (!isTouching(headKnot, knot)) {
                        tailCoordinates[index] = moveTail(knot, headKnot)
                    }
                }
                coordinates.add(tailCoordinates.last())
            }
            // prettyPrint(steps, direction, tailCoordinates, headCoordinates)
        }

        // printResult(coordinates)

        return coordinates.size
    }

    val testInput = readInput("day09_test")
    // test part1
    val resultPart1 = part1(testInput)

    val expectedPart1 = 13 //TODO Change me

    println("Part1\nresult: $resultPart1")
    println("expected:$expectedPart1 \n")
    check(resultPart1 == expectedPart1)

    //Check results part1
    val input = readInput("day09")
    println(6212)
    println("Part1 ${part1(input)}\n\n")

    // test part2
    val testInput2 = readInput("day09_test_2")
    val resultPart2 = part2(testInput2)

    val expectedPart2 = 36 //TODO Change me

    println("Part2\nresult: $resultPart2")
    println("expected:$expectedPart2 \n")
    check(resultPart2 == expectedPart2)

    //Check results part2
    println("Part2 ${part2(input)}")
}
