fun main() {

    data class Tree(val value: Int, var visible: Boolean? = null) {
        // val sightLeft: Int = 0
        // val sightRight: Int = 0
        // val sightTop: Int = 0
        // val sightBottom: Int = 0
        //
        // fun scenicScore(): Int {
        //     return sightLeft * sightRight * sightTop * sightBottom
        // }

        override fun toString(): String {
            return "Tree(value=$value, visible=$visible)"
        }
    }

    fun buildMap(input: List<String>): Array<Array<Tree>> {
        val map = Array(input.size) { Array(input[0].length) { Tree(0, false) } }

        input.mapIndexed { y, line ->
            line.map { it.toString().toInt() }.mapIndexed { x, number ->
                map[y][x] = Tree(number, false)
            }
        }
        return map
    }

    fun printCoordinates(map: Array<Array<Tree>>, width: Int, height: Int) {
        map.forEachIndexed { y, array ->
            array.forEachIndexed { x, tree ->
                if (tree.visible == true && x != 0 && y != 0 && x != width - 1 && y != height - 1) {
                    println("$y,$x(${tree.value})")
                }
            }
            println()
        }
    }

    fun part1(input: List<String>): Int {
        val map = buildMap(input)
        val height = input.size
        val width = input[0].length

        map.forEachIndexed { y, array ->
            array.forEachIndexed { x, tree ->
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    tree.visible = true
                } else {

                    //visible from right
                    try {
                        var neighbourX = x + 1
                        var visible = true
                        while (visible) {
                            if (tree.value <= map[y][neighbourX].value) {
                                visible = false
                            }
                            neighbourX++
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        tree.visible = true
                    }

                    // visible from left
                    try {
                        var neighbourX = x - 1
                        var visible = true
                        while (visible) {
                            if (tree.value <= map[y][neighbourX].value) {
                                visible = false
                            }
                            neighbourX--
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        tree.visible = true
                    }

                    // visible from top
                    try {
                        var neighboury = y - 1
                        var visible = true
                        while (visible) {
                            if (tree.value <= map[neighboury][x].value) {
                                visible = false
                            }
                            neighboury--
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        tree.visible = true
                    }

                    // visible from bottom
                    try {
                        var neighboury = y + 1
                        var visible = true
                        while (visible) {
                            if (tree.value <= map[neighboury][x].value) {
                                visible = false
                            }
                            neighboury++
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        tree.visible = true
                    }
                }
            }
        }

        // printCoordinates(map, width, height)

        return map.flatMap { it.filter { tree -> tree.visible == true } }.size
    }

    fun rightLineOfSight(x: Int, tree: Tree, map: Array<Array<Tree>>, y: Int): Int {
        var sight = 0
        try {
            var neighbourX = x + 1
            while (true) {
                if (tree.value > map[y][neighbourX].value) {
                    // println("${tree.value} > ${map[y][neighbourX].value} for tree:$tree")
                    sight++
                    neighbourX++
                } else {
                    sight+=1
                    break
                }
            }
        } catch (_: IndexOutOfBoundsException) {
        }
        return sight
    }

    fun leftLineOfSight(x: Int, tree: Tree, map: Array<Array<Tree>>, y: Int): Int {
        var sight = 0
        try {
            var neighbourX = x - 1
            while (true) {
                if (tree.value > map[y][neighbourX].value) {
                    sight++
                    neighbourX--
                } else {
                    sight+=1
                    break
                }
            }
        } catch (_: IndexOutOfBoundsException) {
        }
        return sight
    }

    fun topLineOfSight(x: Int, tree: Tree, map: Array<Array<Tree>>, y: Int): Int {
        var sight = 0
        try {
            var neighboury = y - 1
            while (true) {
                if (tree.value > map[neighboury][x].value) {
                    sight++
                    neighboury--
                } else {
                    sight+=1
                    break
                }
            }
        } catch (_: IndexOutOfBoundsException) {
        }
        return sight
    }

    fun bottomLineOfSight(x: Int, tree: Tree, map: Array<Array<Tree>>, y: Int): Int {
        var sight = 0
        try {
            var neighbourY = y + 1
            while (true) {
                if (tree.value > map[neighbourY][x].value) {
                    sight++
                    neighbourY++
                } else {
                    sight+=1
                    break
                }
            }
        } catch (_: IndexOutOfBoundsException) {
        }
        return sight
    }

    fun part2(input: List<String>): Int {
        val map = buildMap(input)

        var scenicHighScore = 0

        map.forEachIndexed { y, array ->
            array.forEachIndexed { x, tree ->

                val rightView = rightLineOfSight(x, tree, map, y)
                val leftView = leftLineOfSight(x, tree, map, y)
                val bottomView = bottomLineOfSight(x, tree, map, y)
                val topView = topLineOfSight(x, tree, map, y)

                val score = rightView * leftView * topView * bottomView
                if (score > scenicHighScore) {
                    scenicHighScore = score
                    println("$tree $x,$y has a new highscore of $scenicHighScore right:$rightView left:$leftView top:$topView bottom:$bottomView")
                }
            }
        }

        return scenicHighScore
    }

    val testInput = readInput("day08_test")
    // test part1
    val resultPart1 = part1(testInput)

    val expectedPart1 = 21 //TODO Change me

    println("Part1\nresult: $resultPart1")
    println("expected:$expectedPart1 \n")
    check(resultPart1 == expectedPart1)

    //Check results part1
    val input = readInput("day08")
    println("Part1 ${part1(input)}\n\n")

    // test part2
    val resultPart2 = part2(testInput)

    val expectedPart2 = 8 //TODO Change me

    println("Part2\nresult: $resultPart2")
    println("expected:$expectedPart2 \n")
    check(resultPart2 == expectedPart2)

    //Check results part2
    println("Part2 ${part2(input)}")
}
