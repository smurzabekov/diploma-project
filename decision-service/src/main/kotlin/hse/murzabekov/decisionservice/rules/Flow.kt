package hse.murzabekov.decisionservice.rules

import hse.murzabekov.decisionservice.domain.DecisionDto
import hse.murzabekov.decisionservice.domain.RequestDto
import hse.murzabekov.decisionservice.domain.Treatment
import hse.murzabekov.decisionservice.service.ExternalStrategyService
import org.springframework.stereotype.Component

@Component
class Flow(
    val costPack: CostPack,
    val surgeryPack: SurgeryPack,
    val remediesPack: RemediesPack,
    val externalStrategyPack: ExternalStrategyPack,
    val externalStrategyService: ExternalStrategyService
) {

    fun calculateDecision(requestDto: RequestDto): DecisionDto {
        var decision = DecisionDto().apply {
            cost = costPack.executeRule(requestDto)
            actionsToDo.add(
                Treatment().apply {
                    remedies = remediesPack.executeRule(requestDto)
                    surgery = surgeryPack.executeRule(requestDto)
                }
            )
        }

        val externalStrategyWrapper = externalStrategyService.setExternalStrategyWrapper(
            externalStrategyService.getExternalStrategy()
        )
        if (externalStrategyWrapper.isPresent()) {
            decision = externalStrategyPack.executeRuleWithExternalStrategy(requestDto, externalStrategyWrapper)
        }

        return decision
    }
}