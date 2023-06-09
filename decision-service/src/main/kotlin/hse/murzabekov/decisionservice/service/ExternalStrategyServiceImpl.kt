package hse.murzabekov.decisionservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import hse.murzabekov.decisionservice.domain.externalstrategy.ExternalStrategyDto
import hse.murzabekov.decisionservice.domain.externalstrategy.ExternalStrategyWrapper
import hse.murzabekov.decisionservice.repository.ExternalStrategyRepository
import org.springframework.stereotype.Service

@Service
class ExternalStrategyServiceImpl(
    val repository: ExternalStrategyRepository,
    val objectMapper: ObjectMapper
) : ExternalStrategyService {

    override fun getExternalStrategy(): ExternalStrategyDto? {
        return objectMapper.readValue(
            repository.getExternalStrategy(1) ?: return null,
            ExternalStrategyDto::class.java
        )
    }

    override fun setExternalStrategyWrapper(externalStrategyDto: ExternalStrategyDto?) =
        ExternalStrategyWrapper(externalStrategyDto)


}