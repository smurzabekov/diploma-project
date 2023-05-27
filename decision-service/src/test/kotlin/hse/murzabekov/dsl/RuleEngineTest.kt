package hse.murzabekov.dsl

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RuleEngineTest {
    val engine: RuleEngine<User> = RuleEngine.getInstance()

    @Test
    fun `add a rule to rule engine and fire`() {
        val rule1 = rule<User>("1.0.0") {
            name = "rule1"
            description = "This is the first rule for testing with one condition and one action"
            condition = {
                it.firstName == "Sultan" && it.lastName == "Murzabekov"
            }
            action = {
                println("Rule 1 is fired")
                println("Second action is executed")
            }
        }
        engine.addRule(rule1)
        assertTrue(engine.fireRule("1.0.0", User(firstName = "Sultan", lastName = "Murzabekov")))
    }

    @Test
    fun `add a rule set to rule engine and fire`() {
        val user = User(firstName = "Sultan", lastName = "Murzabekov", activated = false)
        val s = ruleSet<User> {
            setId = "1.0.0"
            description = "this is the first set of rules"
            rules {
                rule("1.0.0") {
                    name = "rule1"
                    description = "This is the first rule for testing with one condition and one action"
                    condition = {
                        it.firstName == "Sultan" && it.lastName == "Murzabekov"
                    }
                    action = {
                        println("Rule 1 is fired")
                    }
                }
                rule("1.0.0") {
                    name = "rule2"
                    description = "This is the second rule for testing with one condition and one action"
                    condition = or (
                            withFirstName("Sultan"),
                            withLastName("Murzabekov"),
                            withGender(Gender.MALE)
                    )
                    action = {
                        println("Rule 2 is fired")
                    }
                }
            }
        }
        engine.addRuleSet(s)
        assertTrue(engine.fireRuleSet("1.0.0", user))
    }


}