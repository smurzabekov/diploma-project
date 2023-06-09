package hse.murzabekov.decisionservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [
        DataSourceAutoConfiguration::class,
        JdbcTemplateAutoConfiguration::class
    ]
)
class DecisionServiceApplication

fun main(args: Array<String>) {
    runApplication<DecisionServiceApplication>(*args)
}
