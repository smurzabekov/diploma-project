package hse.murzabekov.decisionservice.rules

import hse.murzabekov.decisionservice.domain.DecisionDto
import hse.murzabekov.decisionservice.domain.RequestDto
import hse.murzabekov.decisionservice.domain.Treatment
import org.springframework.stereotype.Component

@Component
class Flow(
    val costPack: CostPack,
    val surgeryPack: SurgeryPack,
    val remediesPack: RemediesPack
) {

    fun calculateDecision(requestDto: RequestDto) = DecisionDto().apply {
        cost = costPack.executeRule(requestDto)
        actionsToDo.add(
            Treatment(
                remedies = remediesPack.executeRule(requestDto),
                surgery = surgeryPack.executeRule(requestDto)
            )
        )
    }
}