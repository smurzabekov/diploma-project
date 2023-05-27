package hse.murzabekov.decisionservice.rules

import hse.murzabekov.decisionservice.domain.RequestDto
import hse.murzabekov.dsl.ruleSet
import org.springframework.stereotype.Component

@Component
class CostPack: Pack<Int> {
    private var cost = 0

    val costRules = ruleSet<RequestDto> {
        setId = "insurance"
        description = "Rule fires when insurance provided or not"
        rules {
            rule("1") {
                name = "insurance-true"
                description = "Executes when insurance is presented"
                condition = {
                    it.insuranceAvailability
                }
                action = {
                    cost = 100000
                }
            }
            rule("2") {
                name = "insurance-false"
                description = "Executes when insurance is absent"
                condition = {
                    !it.insuranceAvailability
                }
                action = {
                    cost = 20000
                }
            }
        }
    }

    override fun executeRule(request: RequestDto): Int {
        costRules.fire(request)
        return cost
    }


}