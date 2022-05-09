package calculator

fun String.isNumber(): Boolean {
    val isIntRegex = Regex("-?\\d+")

    return this.matches(isIntRegex)
}
