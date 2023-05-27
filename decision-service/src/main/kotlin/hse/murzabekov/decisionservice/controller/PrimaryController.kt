package hse.murzabekov.decisionservice.controller

import hse.murzabekov.decisionservice.domain.DecisionDto
import hse.murzabekov.decisionservice.domain.RequestDto
import hse.murzabekov.decisionservice.service.LogicService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PrimaryController(
    val logicService: LogicService
) {

    @PostMapping(value = ["/decision"])
    fun processRequest(
        @RequestBody request: RequestDto,
    ): ResponseEntity<DecisionDto> = ResponseEntity.ok(logicService.processRequest(request))
}