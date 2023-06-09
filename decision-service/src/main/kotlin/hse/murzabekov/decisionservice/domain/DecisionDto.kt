package hse.murzabekov.decisionservice.domain

class DecisionDto {
    val actionsToDo: MutableList<Treatment> = mutableListOf()
    var cost: Int = 10_000
}

class Treatment {
    var remedies: Remedy? = null
    var surgery: Surgery? = null
}

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