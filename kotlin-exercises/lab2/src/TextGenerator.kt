import java.util.Locale
import java.util.StringTokenizer
import kotlin.random.Random

class TextGenerator {
    private val data: MutableMap<String, MutableList<String>> = HashMap()

    fun trainFromText(trainingText: String) {
        val words = trainingText.split( " ")

        for (i in 0 until words.size - 2) {
            val key = "${words[i]} ${words[i + 1]}"

            if (!data.containsKey(key)) {
                data[key] = mutableListOf()
            }
            data[key]?.add(words[i + 2])
        }
        println(data)
    }

    fun generate(startWords: String, numWords: Int): String {


        val generatedText = startWords.split(" ").toMutableList()

        var i = 0
        while (i < generatedText.size - 1 && i < numWords - 2) {
            val key = "${generatedText[i]} ${generatedText[i + 1]}"

            // Check if the word pair exists in the data map
            if (!data.containsKey(key) || data[key]?.isEmpty() == true) {
                break
            }
            val possibleWords = data[key]!!
            val randIndex = Random.nextInt(possibleWords.size)
            generatedText.add(possibleWords[randIndex])
            i++
        }
        return generatedText.joinToString(" ")
    }
}