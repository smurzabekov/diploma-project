package hse.murzabekov.decisionservice.configuration

import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DatabaseConfig {
    @Bean
    fun source(properties: DatabaseProperties): DataSource = HikariDataSource(properties)

    @Bean
    fun jdbcTemplate(
        ds: DataSource
    ) = EngineJdbcTemplate(ds)
}