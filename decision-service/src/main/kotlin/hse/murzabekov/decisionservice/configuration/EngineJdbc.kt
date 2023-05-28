package hse.murzabekov.decisionservice.configuration

import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.PreparedStatementCreatorFactory
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterUtils
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import java.sql.ResultSet
import javax.sql.DataSource


class EngineJdbcTemplate(dataSource: DataSource) : NamedParameterJdbcTemplate(dataSource) {

    fun createPreparedStatementCreator(
        sql: String,
        paramSource: SqlParameterSource
    ): PreparedStatementCreator {
        val parsedSql = getParsedSql(sql)
        val sqlToUse = NamedParameterUtils.substituteNamedParameters(parsedSql, paramSource)
        val declaredParameters = NamedParameterUtils.buildSqlParameterList(parsedSql, paramSource)
        val factory = PreparedStatementCreatorFactory(sqlToUse, declaredParameters).also {
            it.setUpdatableResults(true)
            it.setResultSetType(ResultSet.TYPE_FORWARD_ONLY)
        }
        val params = NamedParameterUtils.buildValueArray(parsedSql, paramSource, null)
        return factory.newPreparedStatementCreator(params)
    }
}