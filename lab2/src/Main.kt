import java.util.*

enum class DictionaryType {
    HASH_SET,
    TREE_SET,
    LIST

}
fun main() {
    /*val dict: IDictionary = HashSetDictionary()
    dict.add("asd")
    println("Number of words: ${dict.size()}")
    var word: String?
    while (true) {
        print("What to find? ")
        word = readLine()
        if (word.equals("quit")) {
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }

    // Request a HashSetDictionary instance (singleton)
    val hashSetDictionary = DictionaryProvider.createDictionary(DictionaryProvider.DictionaryType.HASH_SET)
    hashSetDictionary.add("hello")
    println("HashSetDictionary size: ${hashSetDictionary.size()}")

    // Request a TreeSetDictionary instance (singleton)
    val treeSetDictionary = DictionaryProvider.createDictionary(DictionaryProvider.DictionaryType.TREE_SET)
    treeSetDictionary.add("world")
    println("TreeSetDictionary size: ${treeSetDictionary.size()}")

    // Request an ArrayListDictionary instance (singleton)
    val arrayListDictionary = DictionaryProvider.createDictionary(DictionaryProvider.DictionaryType.LIST)
    arrayListDictionary.add("Kotlin")
    println("ArrayListDictionary size: ${arrayListDictionary.size()}")
*/
    val name = "John Smith"
    println(name.Monogram())

    val fruits = listOf("apple","pear","melon","strawberry")
    println(fruits.joinWithSeparator("#"))
    println(fruits.returnTheLongestWord())

    val currentDate = Date()
    println(currentDate)

    println("The current date leap year status is : ${currentDate.isLeapYear()}")

    val date = Date(2021,12,30)
    println("The date is valid:"+date.isValid())

    var valid = 0
    var randomDateS = mutableListOf<Date>()

    while(randomDateS.size < 10) {
    var randomdate = generateRandomDate()
    if(randomdate.isValid()){
        randomDateS.add(randomdate)
    }
    else{
        println("The date is not valid."+randomdate)
    }
    }
    println("The valid dates:")
    randomDateS.forEach { println(it) }

    var sortedDates = randomDateS.sortBy{ it.year }
    println("The dates sorted:")
    randomDateS.forEach { println(it) }

    var reversedDates = randomDateS.sortByDescending{ it.year }
    println("The dates reversed:")
    randomDateS.forEach { println(it) }

    val sortedDatesCustom = randomDateS.sortedWith(customComparator)
    println("Custom sort:")
    randomDateS.forEach{ println(it) }
}






fun String.Monogram():String {
    return this.split(" ")
        .map { it.first().uppercase() }
        .joinToString(" ")

}


fun List<String>.joinWithSeparator(separator:String):String {
    return this.joinToString(separator)
}

fun List<String>.returnTheLongestWord(): String? {
    return this.maxByOrNull { it.length }
}
