package hse.murzabekov.decisionservice.rules

import hse.murzabekov.decisionservice.domain.DecisionDto
import hse.murzabekov.decisionservice.domain.RequestDto
import hse.murzabekov.decisionservice.domain.Treatment
import hse.murzabekov.decisionservice.domain.externalstrategy.ExternalStrategyWrapper
import hse.murzabekov.dsl.ruleSet
import org.springframework.stereotype.Component

@Component
class ExternalStrategyPack : Pack<DecisionDto> {

    private val decision = DecisionDto().apply {
        actionsToDo.add(
            Treatment()
        )
    }

    fun externalRules(externalStrategyWrapper: ExternalStrategyWrapper) = ruleSet<RequestDto> {
        setId = "External 1"
        description = "Treatment from external strategy"
        rules {
            rule("9") {
                name = "By target disease"
                description = "Remedy to act"
                condition = {
                    it.targetDiseaseType == externalStrategyWrapper.getDiseaseName
                }
                action = {
                    decision.actionsToDo[0].apply {
                        remedies = externalStrategyWrapper.getRemediesToAct
                    }
                }
            }
            rule("10") {
                name = "by allergy on"
                description = "Surgery to do"
                condition = {
                    it.allergyOn.contains(externalStrategyWrapper.getAllergyOnName)
                }
                action = {
                    decision.actionsToDo[0].apply {
                        surgery = externalStrategyWrapper.getSurgeryToDo
                    }
                }
            }
        }
    }

    fun executeRuleWithExternalStrategy(
        request: RequestDto,
        externalStrategyWrapper: ExternalStrategyWrapper
    ): DecisionDto {
        externalRules(externalStrategyWrapper).fire(request)
        return executeRule(request)
    }

    override fun executeRule(request: RequestDto): DecisionDto {
        return decision
    }
}