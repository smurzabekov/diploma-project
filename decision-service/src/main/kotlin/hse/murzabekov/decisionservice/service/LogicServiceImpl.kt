package hse.murzabekov.decisionservice.service

import hse.murzabekov.decisionservice.domain.DecisionDto
import hse.murzabekov.decisionservice.domain.RequestDto
import hse.murzabekov.decisionservice.rules.Flow
import org.springframework.stereotype.Component

@Component
class LogicServiceImpl(
    val decisionByRules: Flow
): LogicService {

    override fun processRequest(request: RequestDto): DecisionDto {
        return decisionByRules.calculateDecision(request)
    }

}