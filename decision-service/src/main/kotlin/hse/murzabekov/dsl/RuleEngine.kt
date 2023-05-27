package hse.murzabekov.dsl


interface RuleEngine<T> {

    fun addRule(rule: Rule<T>)

    fun addRuleSet(ruleSet: RuleSet<T>)

    fun fireRule(id: String, t: T): Boolean

    fun fireRuleSet(setId: String, t: T): Boolean

    companion object {
        @Volatile private var INSTANCE: RuleEngine<*>? = null

        fun <T> getInstance(): RuleEngine<T> {
            INSTANCE ?: synchronized(this) {
                DefaultRuleEngine<T>().also {
                    INSTANCE = it
                }
            }
            return INSTANCE as RuleEngine<T>
        }
    }
}
