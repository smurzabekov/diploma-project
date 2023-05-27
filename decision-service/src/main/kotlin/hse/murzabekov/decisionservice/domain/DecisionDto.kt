package hse.murzabekov.decisionservice.domain

class DecisionDto {
    val actionsToDo: MutableList<Treatment> = mutableListOf()
    var cost: Int = 0
}

data class Treatment(
    val remedies: Remedy,
    val surgery: Surgery?
)

data class Remedy(
    var type: String? = null,
    var dose: Int? = null,
    var frequencyPerDay: Int? = null,
    var periodInDays: Int? = null
)

data class Surgery(
    var procedureName: String? = null,
    var criticalTermInDays: Int? = null
)