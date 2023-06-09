package hse.murzabekov.decisionservice.domain.externalstrategy

import hse.murzabekov.decisionservice.domain.Remedy
import hse.murzabekov.decisionservice.domain.Surgery

data class ExternalStrategyDto(
    val name: String,
    val description: String,
    val diseaseTrigger: DiseaseTrigger,
    val allergyOnTrigger: AllergyOnTrigger
)

class DiseaseTrigger(
    val diseaseName: String,
    val remediesToAct: Remedy
)

class AllergyOnTrigger(
    val allergyName: String,
    val surgeryToDo: Surgery
)