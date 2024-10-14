data class Item(val question: String, val options: List<String>, val correctAnswer: Int)

class ItemRepository {
    private val items = listOf(
        Item("What is Kotlin primarily used for?", listOf("Web development", "Mobile development", "Game development", "Cloud development"), 2),
        Item("What is the correct way to declare a variable in Kotlin?", listOf("let x: Int = 5", "val x = 5", "def x = 5", "int x = 5"), 2),
        Item("What keyword is used to declare a constant in Kotlin?", listOf("const", "final", "val", "var"), 1),
        Item("Which function is used to print something in Kotlin?", listOf("printf()", "printit()", "println()", "write()"), 3),
        Item("Which of the following is a Kotlin feature?", listOf("Null safety", "Multiple inheritance", "Checked exceptions", "Header files"), 1),
        Item("How do you define a function in Kotlin?", listOf("fun functionName(): ReturnType { ... }", "def functionName() -> ReturnType", "function functionName(): ReturnType", "fn functionName() -> ReturnType"), 1),
        Item("What is the default visibility modifier in Kotlin?", listOf("public", "protected", "private", "internal"), 1),
        Item("Which of the following is NOT a data type in Kotlin?", listOf("Byte", "Char", "Boolean", "Real"), 4),
        Item("How do you create an instance of a class in Kotlin?", listOf("new ClassName()", "create ClassName()", "ClassName()", "construct ClassName()"), 3),
        Item("What is the correct way to handle nullable variables in Kotlin?", listOf("Using try/catch", "Using if/else", "Using the ? and !! operators", "Using safe keyword"), 3)
    )

    fun getItems(): List<Item> = items
}

class ItemService(private val repository: ItemRepository) {
    fun getRandomItems(count: Int): List<Item> {
        return repository.getItems().shuffled().take(count)
    }
}

class ItemController(private val service: ItemService) {
    fun runQuiz(numQuestions: Int) {
        val questions = service.getRandomItems(numQuestions)
        var correctAnswers = 0

        for ((index, item) in questions.withIndex()) {
            println("Question ${index + 1}: ${item.question}")
            item.options.forEachIndexed { idx, option -> println("${idx + 1}) $option") }

            print("Your answer (1-${item.options.size}): ")
            val userAnswer = readLine()?.toIntOrNull()

            if (userAnswer != null && userAnswer == item.correctAnswer) {
                println("Correct!\n")
                correctAnswers++
            } else {
                println("Wrong! The correct answer is: ${item.correctAnswer}\n")
            }
        }

        println("Quiz finished! Your score: $correctAnswers/${questions.size}")
    }
}

fun main() {
    val repository = ItemRepository()
    val service = ItemService(repository)
    val controller = ItemController(service)

    print("How many questions would you like to answer? ")
    val numQuestions = readLine()?.toIntOrNull() ?: 5
    controller.runQuiz(numQuestions)
}
