package hse.murzabekov.decisionservice.rules

import hse.murzabekov.decisionservice.domain.RequestDto


interface Pack<T> {

    fun executeRule(request: RequestDto) : T
}