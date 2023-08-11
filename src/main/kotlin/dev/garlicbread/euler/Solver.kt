package dev.garlicbread.euler

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.measureNanoTime

fun <T : Any> solve(block: () -> Problem<T>) {
    printResult(block)
}

fun getInput(problem: Int): List<String>? {
    val resource = "input/problem$problem.in"
    return Problem::class.java.classLoader?.getResource(resource)
        ?.toURI()
        ?.let { Paths.get(it) }
        ?.let { Files.readAllLines(it) }
}

fun printResult(block: () -> Problem<*>) {
    lateinit var problem: Problem<*>
    lateinit var problemResult: String
    var problemInitTime = 0L
    var problemTime = 0L
    var problemTotalTime = 0L

    problemInitTime += measureNanoTime { problem = block() }
    problemTime += measureNanoTime {
        problemResult = problem.solveProblem()!!.bold("36")
    }
    problemTotalTime += problemInitTime + problemTime

    println("Problem ${problem.problem}".bold("32"))
    println("Result: $problemResult")
    println("Init Time: ${"%.3fms".format(problemInitTime.toMillis()).bold("33")}")
    println("Problem Time: ${"%.3fms".format(problemTime.toMillis()).bold("33")}")
    println("Total Time: ${"%.3fms".format(problemTotalTime.toMillis()).bold("33")}")
}

private fun Long.toMillis() = this / 1000000f

private fun Any.bold(color: String = ""): String = style("$color;1")

private fun Any.style(color: String): String {
    return "\u001B[${color}m$this\u001B[0m"
}
