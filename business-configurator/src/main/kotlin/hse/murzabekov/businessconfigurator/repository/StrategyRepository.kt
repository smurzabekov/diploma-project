package hse.murzabekov.businessconfigurator.repository

import hse.murzabekov.businessconfigurator.configuration.EngineJdbcTemplate
import org.springframework.stereotype.Repository

private const val INSERT = "INSERT INTO external_strategy (data) VALUES (:strategyBody)"

@Repository
class StrategyRepository(
    val jdbcTemplate: EngineJdbcTemplate
) {

    fun addNewStrategy(strategyBody: String) {
        val params = mapOf(
            "strategyBody" to strategyBody
        )

        jdbcTemplate.update(INSERT, params)
    }
}