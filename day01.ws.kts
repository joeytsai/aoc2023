import java.nio.charset.Charset
import java.nio.file.Path
import java.nio.file.Paths


fun readInput(filename: String): String {
    val path = Paths.get("/Users/joey/dev/github/aoc2023/$filename")
    val file = path.toFile()
    return file.inputStream().readBytes().toString(Charset.defaultCharset())
}

val file = "day01.txt"

fun printInput()  {
    val input = readInput(file)
    val lines = input.lines()
    for ((i, line) in lines.withIndex()) {
        println("line $i=$line")
    }
}

fun bar(): String {
    val lines = readInput(file).lines()
    var sum = 0
    lines.forEach { line ->
        val first = line.firstOrNull { it.isDigit() }?.digitToInt()
        if (first != null) {
            val last = line.last { it.isDigit() }.digitToInt()
            val cv = "$first$last".toInt()
            sum += cv
            println("cv=$cv")
        }
    }
    return sum.toString()
}

//printInput()
val b = bar()
println("answer=$b")
