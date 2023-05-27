package hse.murzabekov.dsl


class RuleSet<T>(val setId: String, val description: String, val rules: List<Rule<T>>) {

    fun fire(t: T): Boolean {
        var result : Boolean = false;
        rules.forEach() {
            var r = it.fire(t)
            result = result || r
        }
        return result
    }
}

class RuleSetBuilder<T> {
    var setId: String = ""
    var description: String = ""
    var rules = mutableListOf<Rule<T>>()

    fun rules(block: RULES<T>.() -> Unit) {
        rules.addAll(RULES<T>().apply(block))
    }
    fun build(): RuleSet<T> = RuleSet<T>(setId, description, rules)
}

class RULES<T>: ArrayList<Rule<T>>() {
    fun rule(id: String, block: Rule.Builder<T>.() -> Unit) {
        add(Rule.Builder<T>(id).apply(block).build())
    }
}

fun <T> ruleSet(block: RuleSetBuilder<T>.() -> Unit): RuleSet<T> = RuleSetBuilder<T>().apply(block).build()

