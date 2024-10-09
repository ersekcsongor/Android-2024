import java.util.TreeSet

class TreeSetDictionary : IDictionary {

    private val words: TreeSet<String> = TreeSet()

    override fun add(word: String): Boolean {
        return if (words.contains(word)) {
            false
        } else {
            words.add(word)
            true
        }
    }

    override fun find(word: String): Boolean {
        return words.contains(word)
    }

    override fun size(): Int {
        return words.size
    }
}
