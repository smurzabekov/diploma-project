package hse.murzabekov.businessconfigurator.configuration

import com.zaxxer.hikari.HikariConfig
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "datasource")
@Configuration
class DatabaseProperties : HikariConfig()