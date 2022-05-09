package calculator

class Calculator {
    fun input(expression: String?): Int {
        requireNotNull(expression) { "null 입니다." }
        require(expression.isNotEmpty()) { "빈 공백 문자열입니다." }

        val expressionContents: List<String> = expression.split(" ")

        var result = expressionContents.first().toInt()
        var operation: String? = null

        expressionContents.drop(1).forEach { content ->
            checkOperation(content)

            if (isOperation(content)) {
                operation = content
                return@forEach
            }

            result = calculate(first = result, second = content.toInt(), operation = operation)
        }

        return result
    }

    private fun calculate(first: Int, second: Int, operation: String?): Int {
        return when (operation) {
            "+" -> plus(first, second)
            "-" -> minus(first, second)
            "*" -> multiply(first, second)
            "/" -> division(first, second)
            else -> first
        }
    }

    private fun checkOperation(content: String) {
        if (isNumber(content).not() && isOperation(content).not()) {
            throw IllegalArgumentException("사칙연산 기호가 아닌 문자가 들어있습니다.")
        }
    }

    private fun isNumber(content: String): Boolean {
        val isIntRegex = Regex("-?\\d+")

        return content.matches(isIntRegex)
    }

    private fun isOperation(content: String): Boolean {
        val isOperationRegex = Regex("[+\\-*/]")

        return content.matches(isOperationRegex)
    }

    private fun plus(first: Int, second: Int): Int = first + second

    private fun minus(first: Int, second: Int): Int = first - second

    private fun multiply(first: Int, second: Int): Int = first * second

    private fun division(first: Int, second: Int): Int = first / second
}
