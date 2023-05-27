package hse.murzabekov.dsl

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RuleTest {

    @Test
    fun `a simple rule with string fact`() {
        val fact = "Kotlin"

        val condition1: Predicate<String> = {
            it.startsWith("Kot")
        }

        val action1: () -> Unit = {
            println("Rule 1 is fired")
        }

        val rule = rule<String>("com.networknt.rule0001-1.0.0") {
            name = "rule 1"
            description = "This is the first rule for testing"
            condition = condition1
            action = action1
        }

        val result = rule.fire(fact)
        assertTrue(result)
        println(result)
    }

    @Test
    fun `a simple rule with one condition and one action`() {
        val rule = rule<User>("1.0.0") {
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
        val result = rule.fire(User(firstName = "Sultan", lastName = "Murzabekov"))
        println(result)
    }

    @Test
    fun `a rule with or conditions`() {
        val rule = rule<User>("2-1.0.0") {
            name = "rule 0002"
            description = "This is the rule for testing with multiple conditions compound with or"
            condition = or (
                    withFirstName("Sultan"),
                    withLastName("Murzabekov"),
                    withGender(Gender.MALE)
                )
            action = {
                println("Rule 2 is fired")
                println("Second action is executed")
            }

        }
        val result = rule.fire(User(firstName = "Sultan", lastName = "Murzabekov"))
        assertTrue(result)
        println(result)

    }

    @Test
    fun `a rule with and conditions`() {
        val rule = rule<User>("3-1.0.0") {
            name = "rule3"
            description = "This is the rule for testing with multiple conditions compound with and"
            condition = and (
                    withFirstName("Sultan"),
                    withLastName("Murzabekov"),
                    withGender(Gender.MALE),
                    withActivated(true)  // this condition fails.
            )
            action = {
                println("Rule 3 is fired")
                println("Second action is executed")
            }
        }
        val result = rule.fire(User(firstName = "Sultan", lastName = "Murzabekov"))
        assertFalse(result)
        println(result)
    }
}
