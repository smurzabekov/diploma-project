package hse.murzabekov.decisionservice.domain

data class RequestDto(
    val id: Int,
    val targetDiseaseType: String,
    val chronicDiseases: List<String>,
    val allergyOn: List<String>,
    val bodyTemperature: Double,
    val numberOfHitsInTheLastMonth: Int,
    val insuranceAvailability: Boolean
)