package hse.murzabekov.decisionservice.rules

import hse.murzabekov.decisionservice.domain.RequestDto
import hse.murzabekov.dsl.DefaultRuleEngine
import org.springframework.stereotype.Component

@Component
class MedicalRulesEngine: DefaultRuleEngine<RequestDto>()