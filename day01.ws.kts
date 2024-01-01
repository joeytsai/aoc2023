import java.nio.charset.Charset
import java.nio.file.Paths


fun readInputLines(filename: String): List<String> {
    val path = Paths.get("/Users/joey/dev/github/aoc2023/$filename")
    val file = path.toFile()
    val input = file.inputStream().readBytes().toString(Charset.defaultCharset())
    return input.lines()
}

val file = "day01.txt"
val lines = readInputLines(file)

fun printInput()  {
    for ((i, line) in lines.withIndex()) {
        println("line $i=$line")
    }
}

fun part1(): String {
    val lines = readInputLines("day01.txt")
    var sum = 0
    lines.forEach { line ->
        if (line.isNotBlank()) {
            val first = line.first { it.isDigit() }.digitToInt()
            val last = line.last { it.isDigit() }.digitToInt()
            val cv = "$first$last".toInt()
            sum += cv
            //println("cv=$cv")
        }
    }
    return sum.toString()
}

val digitWords = "one, two, three, four, five, six, seven, eight, nine"
        .split(",").map { it.trim() }
val digits = digitWords.mapIndexed { i, s -> s to i+1 }.toMap()

fun part2(): String {
    var sum = 0
    lines.forEach { line ->
        val (first, last) = firstAndLastNumber(line)
        val cv = "$first$last".toInt()
        sum += cv
        //println("cv=$cv")
    }
    return sum.toString()
}

val numbers = buildList() {
    addAll(digitWords)
    addAll((1..9).map{it.toString()})
}

fun firstAndLastNumber(line: String): Pair<Int, Int> {
    if (line.isBlank()) return 0 to 0
    val firstIndex = line.indexOfAny(numbers, 0)
    val first = numberAt(line, firstIndex)
    var last = first
    var startIndex = firstIndex
    do {
        startIndex += 1
        var index = line.indexOfAny(numbers, startIndex)
        if (index != -1) last = numberAt(line, index)
    } while (index != -1)

    return first to last
}

fun numberAt(line: String, index: Int): Int {
    assert(index >= 0)
    if (line[index].isDigit()) return line[index].digitToInt()
    val sub = line.substring(index)
    for ((digit, v) in digits) {
        if (sub.startsWith(digit)) return v
    }
    println("couldn't find numberAt($line, $index)!")
    return -1
}

//printInput()
val part1 = part1()
println("part 1 answer=$part1")
val part2 = part2()
println("part 2 answer=$part2")
