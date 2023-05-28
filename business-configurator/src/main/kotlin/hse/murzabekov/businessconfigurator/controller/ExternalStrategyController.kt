package hse.murzabekov.businessconfigurator.controller

import hse.murzabekov.businessconfigurator.repository.StrategyRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class ExternalStrategyController(
    val strategyRepo: StrategyRepository
) {

    @PostMapping(value = ["/insert"])
    fun insertStrategy(
        @RequestBody strategyBody: String
    ) = ResponseEntity.ok(strategyRepo.addNewStrategy(strategyBody))
}