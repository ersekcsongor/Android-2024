object DictionaryProvider {

    // Enum to specify the type of dictionary
    enum class DictionaryType {
        HASH_SET,
        TREE_SET,
        LIST
    }

    // Singletons for different dictionary types
    private val hashSetDictionary: IDictionary by lazy { HashSetDictionary() }
    private val treeSetDictionary: IDictionary by lazy { TreeSetDictionary() }
    private val ListDictionary: IDictionary by lazy { ListDictionary() }

    // Method to create or return the required singleton dictionary
    fun createDictionary(type: DictionaryType): IDictionary {
        return when (type) {
            DictionaryType.HASH_SET -> hashSetDictionary
            DictionaryType.TREE_SET -> treeSetDictionary
            DictionaryType.LIST -> ListDictionary
        }
    }
}
