package hse.murzabekov.dsl

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RuleSetTest {

    @Test
    fun `a simple set with two rules`() {
        val user = User(firstName = "Sultan", lastName = "Murzabekov", activated = false)
        val s = ruleSet<User> {
            setId = "set001"
            description = "this is the first set of rules"
            rules {
                rule("1.0.0") {
                    name = "rule 0001"
                    description = "This is the first rule for testing with one condition and one action"
                    condition = {
                        it.firstName == "Sultan" && it.lastName == "Murzabekov"
                    }
                    action = {
                        println("Rule 1 is fired")
                    }
                }
                rule("2-1.0.0") {
                    name = "rule 0002"
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
        assertTrue(s.fire(user))
    }
}