fun main() {
    class Node(
        val value: Int,
        val children: MutableList<Node> = mutableListOf(),
        val name: String,
        val parent: Node? = null,
        var folderSize: Int = 0
    ) {
        override fun toString(): String {
            return "Node(name='$name', value=$value)"
        }
    }

    var lessThanCutOff = mutableListOf<Int>()

    fun foldersLessThan(parentNode: Node): Int {
        val size = parentNode.children.sumOf { node ->
            if (node.children.isNotEmpty()) {
                foldersLessThan(node)
            } else {
                node.value
            }
        }

        parentNode.folderSize = size
        if (size < 100_000) {
            lessThanCutOff.add(size)
        }

        return size
    }

    fun buildTree(input: List<String>): Node {
        val root = Node(0, name = "/")

        var currentNode = root
        input.forEach { line ->
            if (line.startsWith("$ cd")) {
                when (val dirToGoTo = line.split(" ")[2]) {
                    "/" -> currentNode = root
                    ".." -> currentNode = currentNode.parent!!
                    else -> currentNode = currentNode.children.first { it.name == dirToGoTo }
                }
            } else if (line == "$ ls") {
                // do nothing
            } else if (!line.startsWith("$")) {
                val split = line.split(" ")

                if (line.startsWith("dir")) {
                    currentNode.children.add(Node(0, name = split[1], parent = currentNode))
                } else {
                    currentNode.children.add(Node(split[0].toInt(), name = split[1], parent = currentNode))
                }
            }
        }
        return root
    }

    fun part1(input: List<String>): Int {
        lessThanCutOff = mutableListOf()
        val root = buildTree(input)

        foldersLessThan(root)

        return lessThanCutOff.sum()
    }

    fun calculateFolderSize(parentNode: Node): Int {
        val size = parentNode.children.sumOf { node ->
            if (node.children.isNotEmpty()) {
                calculateFolderSize(node)
            } else {
                node.value
            }
        }

        parentNode.folderSize = size
        return size
    }

    fun getFoldersBiggerThan(parentNode: Node, minimalSize: Int, result: MutableList<Node>): List<Node> {
        val size = parentNode.children.forEach { node ->
            if (node.children.isNotEmpty()) {
                getFoldersBiggerThan(node, minimalSize, result)
            } else {
                node.value
            }
        }

        if (parentNode.folderSize > minimalSize) {
            result.add(parentNode)
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val root = buildTree(input)

        calculateFolderSize(root)

        val spaceLeft = 70000000 - root.folderSize
        val neededSpace = 30000000 - spaceLeft

        val result = getFoldersBiggerThan(root, neededSpace, mutableListOf())
        return result.map { it.folderSize }.minOf { it }
    }

    val testInput = readInput("day07_test")
    // test part1
    val resultPart1 = part1(testInput)

    val expectedPart1 = 95437 //TODO Change me

    println("Part1\nresult: $resultPart1")
    println("expected:$expectedPart1 \n")
    check(resultPart1 == expectedPart1)

    //Check results part1
    val input = readInput("day07")
    println("Part1 ${part1(input)}\n\n")
    // 1402048

    // test part2
    val resultPart2 = part2(testInput)

    val expectedPart2 = 24933642 //TODO Change me

    println("Part2\nresult: $resultPart2")
    println("expected:$expectedPart2 \n")
    check(resultPart2 == expectedPart2)

    //Check results part2
    println("Part2 ${part2(input)}")
    // 9494924 <- too low
}
