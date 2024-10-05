import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlin.random.Random
import org.junit.Test

fun main() {
    println("Hello World!")
    val x = 5;
    val y = 12;
    println(sum(x,y))
    val weekdays = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    for (days in weekdays) {
        println(days)
    }
    println("Days starting with T:")
    val daysStartingWithT = weekdays.filter { it.startsWith("T") }
    for (day in daysStartingWithT) { println(day) }

    val daysContainingTheLetterE = weekdays.filter { it.contains("e") }
    println("Days containing the letter e:")
    for (day in daysContainingTheLetterE) { println(day) }

    println("Days with length 6:")
    weekdays.filter { it.length == 6 }.forEach { println(it) }

    println("Prime numbers from "+1+" to "+ y)
   for (i in 1..y) {
        if(isPrime(i))
        {
            println("prime:" + i)
        }
    }

    val originalMessage = "Hello World!"

    val encodedMessage = messageCoding(originalMessage, ::encode)
    println("Encoded Message: $encodedMessage")

    val decodedMessage = messageCoding(encodedMessage, ::decode)
    println("Decoded Message: $decodedMessage")


    val numbers = listOf(1,2,3,4,5,6,7,8,9,10)
    printEvenNumbers(numbers)

    val doubledNumbers = numbers.map{ it * 2 }
    println(doubledNumbers)

    val capitalizedDays = weekdays.map { it.uppercase() }
    println(capitalizedDays)

    val firstWordofDays = weekdays.map { it[0] }
    println(firstWordofDays)

    val lenghtOfDays = weekdays.map { it.length }
    println(lenghtOfDays)

    val avgLength = lenghtOfDays.average()
    println("average length: " + avgLength)

    val mutableWeekDays = weekdays.toMutableList()
    mutableWeekDays.removeIf { it.contains("n") }
    println(mutableWeekDays)

    for ( (index,day) in mutableWeekDays.withIndex() )
    {
        println("Item at index $index is $day")
    }
    mutableWeekDays.sort()
    println(mutableWeekDays)

    val randomNumbers = Array(10) { Random.nextInt(0,101)}
    randomNumbers.forEach { println(it) }

    randomNumbers.sort()
    println("Sorted numbers:")
    randomNumbers.forEach { print("$it ") }

    val evenNumbers = randomNumbers.filter { it % 2 == 0 }
    println("\nEven numbers:")
    evenNumbers.forEach {  print("$it ") }

    if(evenNumbers.size == randomNumbers.size){println("All the numbers are even")}
    else{
        println("\nNot all the numbers are even")
    }

    val averageOfRandom = randomNumbers.average()

    println("Average of random: $averageOfRandom" )
    println("Numbers:")
    randomNumbers.forEach { print("$it ") }

    //extra
    println("\n")
    val input1 = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    val input2 = arrayOf("eat", "tEa", "Tan", "atE", "NAT", "bat")
    val input3 = arrayOf("eat")
    val input4 = emptyArray<String>()

    println("Test 1: " + groupAnagrams(input1))
    println("Test 2: " + groupAnagrams(input2))
    println("Test 3: " + groupAnagrams(input3))
    println("Test 4: " + groupAnagrams(input4))
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun isPrime(n: Int): Boolean {
    if (n <= 1) return false

    for (i in 2 until n) {
        if(n % i == 0) return false
    }
    return true
}

fun encode(msg: String): String {
    val shift = 3
    return msg.map { char ->
        if (char.isLetter()) {
            val base = if (char.isUpperCase()) 'A' else 'a'
            ((char - base + shift) % 26 + base.code).toChar()
        } else {
            char
        }
    }.joinToString("")
}

fun decode(msg: String): String {
    val shift = 3
    return msg.map { char ->
        if (char.isLetter()) {
            val base = if (char.isUpperCase()) 'A' else 'a'
            ((char - base - shift + 26) % 26 + base.code).toChar()
        } else {
            char
        }
    }.joinToString("")
}

fun messageCoding(msg: String, func: (String) -> String): String {
    return func(msg)
}

fun printEvenNumbers(numbers: List<Int>) {
    numbers.filter { it % 2 == 0 }.forEach { println(it)}
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()

    for (str in strs) {
        // Convert to lowercase for case-insensitivity
        val sortedStr = str.lowercase().toCharArray().sorted().joinToString("")
        // Add the word to the corresponding anagram group
        map.computeIfAbsent(sortedStr) { mutableListOf() }.add(str.lowercase())
    }

    return map.values.toList()
}



class AnagramsGrouperTest {

    @Test
    fun threeGroupsAllLowerCase() {
        val anagrams = groupAnagrams(listOf("eat", "tea", "tan", "ate", "nat", "bat").toTypedArray())
        assertEquals(3, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat", "tea", "ate")))
        assertTrue(anagrams.contains(listOf("tan", "nat")))
        assertTrue(anagrams.contains(listOf("bat")))
    }

    @Test
    fun threeGroupsSomeUpperCase() {
        val anagrams = groupAnagrams(listOf("eat", "tEa", "Tan", "atE", "NAT", "bat").toTypedArray())
        assertEquals(3, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat", "tea", "ate")))
        assertTrue(anagrams.contains(listOf("tan", "nat")))
        assertTrue(anagrams.contains(listOf("bat")))
    }

    @Test
    fun validOneGroup() {
        val anagrams = groupAnagrams(listOf("eat").toTypedArray())
        assertEquals(1, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat")))
    }

    @Test
    fun noGroup() {
        val anagrams = groupAnagrams(emptyList<String>().toTypedArray())
        assertEquals(0, anagrams.size)
    }
}