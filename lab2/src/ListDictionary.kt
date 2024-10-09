class ListDictionary : IDictionary {

    private val words: MutableList<String> = mutableListOf()

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
