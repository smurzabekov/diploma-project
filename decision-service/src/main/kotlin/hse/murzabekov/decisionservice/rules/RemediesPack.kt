package hse.murzabekov.decisionservice.rules

import hse.murzabekov.decisionservice.domain.Remedy
import hse.murzabekov.decisionservice.domain.RequestDto
import hse.murzabekov.dsl.ruleSet
import org.springframework.stereotype.Component

@Component
class RemediesPack: Pack<Remedy> {

    private val remedies = Remedy()

    val remediesRule = ruleSet<RequestDto> {
        setId = "Remedy 1"
        description = "Remedy for patient"
        rules {
            rule("5") {
                name = "type"
                description = "type remedy"
                condition = {
                    it.bodyTemperature > 36.6
                }
                action = {
                    remedies.type = "Paracetomol"
                }
            }
            rule("6") {
                name = "duration - cold"
                description = "duration and frequency"
                condition = {
                    it.targetDiseaseType == "Cold"
                }
                action = {
                    remedies.frequencyPerDay = 3
                    remedies.periodInDays = 7
                }
            }
            rule("7") {
                name = "duration - apendix"
                description = "duration and frequency"
                condition = {
                    it.targetDiseaseType == "Apendix"
                }
                action = {
                    remedies.frequencyPerDay = 1
                    remedies.periodInDays = 3
                }
            }
            rule("8") {
                name = "dose"
                description = "dose"
                condition = {
                    it.targetDiseaseType == "Apendix"
                            || it.targetDiseaseType == "Cold"
                }
                action = {
                    remedies.dose = 100
                }
            }
        }
    }

    override fun executeRule(request: RequestDto): Remedy {
        remediesRule.fire(request)
        return remedies
    }

}