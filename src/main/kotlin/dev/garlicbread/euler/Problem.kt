package dev.garlicbread.euler

abstract class Problem<T>(val problem: Int) {
    val rawInput = getInput(problem)
    abstract val input: Any?

    abstract fun solveProblem(): T
}
