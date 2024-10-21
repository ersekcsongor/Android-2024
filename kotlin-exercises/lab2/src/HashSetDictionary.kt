class HashSetDictionary : IDictionary {

    private val words: HashSet<String> = HashSet()

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



