package hse.murzabekov.decisionservice.service

import hse.murzabekov.decisionservice.domain.externalstrategy.ExternalStrategyDto
import hse.murzabekov.decisionservice.domain.externalstrategy.ExternalStrategyWrapper

interface ExternalStrategyService {

    fun getExternalStrategy(): ExternalStrategyDto?

    fun setExternalStrategyWrapper(externalStrategyDto: ExternalStrategyDto?): ExternalStrategyWrapper

}