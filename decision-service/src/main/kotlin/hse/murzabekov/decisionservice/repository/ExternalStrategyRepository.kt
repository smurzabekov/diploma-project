package hse.murzabekov.decisionservice.repository

import hse.murzabekov.decisionservice.configuration.EngineJdbcTemplate
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Repository
import java.sql.ResultSet

private const val SQL_GET_BY_TASK_ID = """
    SELECT * from external_strategy WHERE id = :id
"""

@Repository
class ExternalStrategyRepository(
    val jdbcTemplate: EngineJdbcTemplate
) {

    fun getExternalStrategy(id: Long) = try {
        jdbcTemplate.queryForObject(
            SQL_GET_BY_TASK_ID,
            mapOf("id" to id)
        ) { rs: ResultSet, _: Int ->
            rs.getString("data")
        }
    } catch (e: EmptyResultDataAccessException) {
        null
    }
}