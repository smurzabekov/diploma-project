package hse.murzabekov.decisionservice.service

import hse.murzabekov.decisionservice.domain.DecisionDto
import hse.murzabekov.decisionservice.domain.RequestDto


interface LogicService {

    fun processRequest(request: RequestDto): DecisionDto
}