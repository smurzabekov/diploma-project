package hse.murzabekov.decisionservice.rules

import hse.murzabekov.decisionservice.domain.RequestDto
import hse.murzabekov.decisionservice.domain.Surgery
import hse.murzabekov.dsl.*
import org.springframework.stereotype.Component

@Component
class SurgeryPack : Pack<Surgery> {

    private val surgery = Surgery()

    val surgeryRule = ruleSet<RequestDto> {
        setId = "Surgery 1"
        description = "Surgery params"
        rules {
            rule("3") {
                name = "Surgery is necessary?"
                description = "chronicDiseases + allergyOn + body temperature + number of hits > 3"
                condition = and(
                    chronicDiseaseIsNotEmpty(),
                    allergyOnIsNotEmpty(),
                    highBodyTemperature()
                )
                action = {
                    surgery.procedureName = "Operation"
                }
            }
            rule("Determine term") {
                name = "4"
                description = "This is the second rule for testing with one condition and one action"
                condition = {
                    it.bodyTemperature > 37.0
                            && it.targetDiseaseType == "Apendix"
                }
                action = {
                    surgery.criticalTermInDays = 1
                }
            }
        }
    }


    override fun executeRule(request: RequestDto): Surgery {
        surgeryRule.fire(request)
        return surgery
    }
}

fun chronicDiseaseIsNotEmpty() = Condition<RequestDto> { it.chronicDiseases.isNotEmpty() }
fun allergyOnIsNotEmpty() = Condition<RequestDto> { it.allergyOn.isNotEmpty() }
fun highBodyTemperature() = Condition<RequestDto> { it.bodyTemperature > 38.0 }
