fun main() {
    fun part1(input: List<String>): Int {
        val calories = mutableListOf<Int>()

        val newLines = input.withIndex().filter { x -> x.value.isEmpty() }.map { it.index }.toMutableList()
        newLines.add(input.size)

        var start = 0
        newLines.map { index ->
            val caloriesAsInt = input.subList(start + 1, index).map { stringValue -> stringValue.toInt() }
            val sumOfCalories = caloriesAsInt.sumOf { calorie -> calorie }
            start = index

            calories.add(sumOfCalories)
        }

        return calories.max()
    }

    fun part2(input: List<String>): Int {
        val calories = mutableListOf<Int>()

        val newLines = input.withIndex().filter { x -> x.value.isEmpty() }.map { it.index }.toMutableList()
        newLines.add(input.size)

        var start = 0

        newLines.map { index ->
            val caloriesAsInt = input.subList(start + 1, index).map { stringValue -> stringValue.toInt() }
            val sumOfCalories = caloriesAsInt.sumOf { calorie -> calorie }
            start = index

            calories.add(sumOfCalories)
        }

        return calories.toMutableList().sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")

    val result = part1(testInput)
    val expected = 24000
    println("test result: $result")
    println("test expected:$expected \n")

    check(result == expected)


    val input = readInput("Day01")
    println("Part1 ${part1(input)}")
    println("Part2 ${part2(input)}")
}
