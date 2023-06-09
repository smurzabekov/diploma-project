package hse.murzabekov.decisionservice.domain.externalstrategy

class ExternalStrategyWrapper(private val externalStrategyDto: ExternalStrategyDto? = null) {

    val getDiseaseName
        get() = externalStrategyDto!!.diseaseTrigger.diseaseName

    val getAllergyOnName
        get() = externalStrategyDto!!.allergyOnTrigger.allergyName

    val getRemediesToAct
        get() = externalStrategyDto!!.diseaseTrigger.remediesToAct

    val getSurgeryToDo
        get() = externalStrategyDto!!.allergyOnTrigger.surgeryToDo

    fun isPresent() = externalStrategyDto != null
}