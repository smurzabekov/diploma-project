package hse.murzabekov.dsl

open class DefaultRuleEngine<T>: RuleEngine<T> {

    protected val ruleMap: MutableMap<String, Rule<T>> = mutableMapOf()
    protected val ruleSetMap: MutableMap<String, RuleSet<T>> = mutableMapOf()

    override fun addRule(rule: Rule<T>) {
        ruleMap[rule.id] = rule
    }

    override fun addRuleSet(ruleSet: RuleSet<T>) {
        ruleSetMap[ruleSet.setId] = ruleSet
    }

    override fun fireRule(id: String, t: T): Boolean {
        return ruleMap[id]?.fire(t) ?: false
    }

    override fun fireRuleSet(setId: String, t: T): Boolean {
        return ruleSetMap[setId]?.fire(t) ?: false
    }
}
