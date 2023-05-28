package hse.murzabekov.businessconfigurator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [
        DataSourceAutoConfiguration::class,
        JdbcTemplateAutoConfiguration::class
    ]
)
class BusinessConfiguratorApplication

fun main(args: Array<String>) {
    runApplication<BusinessConfiguratorApplication>(*args)
}
